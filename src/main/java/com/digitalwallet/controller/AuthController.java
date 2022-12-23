package com.digitalwallet.controller;

import com.digitalwallet.Exception.RoleIsNotFoundException;
import com.digitalwallet.dto.request.UserLoginRequestDto;
import com.digitalwallet.dto.request.UserRegisterRequestDto;
import com.digitalwallet.dto.response.UserRegisterResponseDto;
import com.digitalwallet.dto.response.UserResponseDto;
import com.digitalwallet.entity.RefreshToken;
import com.digitalwallet.entity.Role;
import com.digitalwallet.entity.RoleType;
import com.digitalwallet.entity.User;
import com.digitalwallet.mapper.request.UserLoginRequestMapper;
import com.digitalwallet.mapper.request.UserRegisterRequestMapper;
import com.digitalwallet.mapper.response.UserRegisterResponseMapper;
import com.digitalwallet.mapper.response.UserResponseMapper;
import com.digitalwallet.messages.ResponseMessage;
import com.digitalwallet.repository.RoleRepository;
import com.digitalwallet.repository.UserRepository;
import com.digitalwallet.service.AuthService;
import com.digitalwallet.service.RefreshTokenService;
import com.digitalwallet.service.UserDetailsImpl;
import com.digitalwallet.utils.JwtUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    final
    AuthenticationManager authenticationManager;

    private final RefreshTokenService refreshTokenService;
    final
    UserRepository userRepository;

    final
    RoleRepository roleRepository;

    final
    PasswordEncoder encoder;

    final
    JwtUtils jwtUtils;

    private final UserLoginRequestMapper userLoginRequestMapper;

    private final UserRegisterRequestMapper userRegisterRequestMapper;

    private final UserRegisterResponseMapper userRegisterResponseMapper;
    private final UserResponseMapper userResponseMapper;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils, UserLoginRequestMapper userLoginRequestMapper, UserRegisterRequestMapper userRegisterRequestMapper, UserRegisterResponseMapper userRegisterResponseMapper, UserResponseMapper userResponseMapper, AuthService authService, RefreshTokenService refreshTokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.userLoginRequestMapper = userLoginRequestMapper;
        this.userRegisterRequestMapper = userRegisterRequestMapper;
        this.userRegisterResponseMapper = userRegisterResponseMapper;
        this.userResponseMapper = userResponseMapper;
        this.authService = authService;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserLoginRequestDto loginRequest) {

        User user = userLoginRequestMapper.toBaseEntity(loginRequest);

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);


        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();


        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
        ResponseCookie jwtRefreshCookie = jwtUtils.generateRefreshJwtCookie(refreshToken.getToken());


        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());


        //todo make a mapper interface
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(userDetails.getId());
        userResponseDto.setUserName(userDetails.getUsername());
        userResponseDto.setRolesList(roles);
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
                .body(userResponseDto);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterRequestDto registerRequest) {
        User user = userRegisterRequestMapper.toBaseEntity(registerRequest);
        UserRegisterResponseDto userRegisterResponseDto = userRegisterResponseMapper.toBaseDto(user);
        if (userRepository.existsByUserName(registerRequest.getUserName())) {

            ResponseMessage responseMessage = ResponseMessage.withResponseData(userRegisterResponseDto, "Error: Username is already taken!", "info");
            return ResponseEntity.badRequest().body(responseMessage);
        }


        // encode the password
        // user.setPassword(encoder.encode(registerRequest.getPassword()));

        Set<String> strRoles = registerRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByRoleType(RoleType.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByRoleType(RoleType.ROLE_ADMIN)
                                .orElseThrow(() -> new RoleIsNotFoundException());
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByRoleType(RoleType.ROLE_MODERATOR)
                                .orElseThrow(() -> new RoleIsNotFoundException());
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByRoleType(RoleType.ROLE_USER)
                                .orElseThrow(() -> new RoleIsNotFoundException());
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        //String userName, String firstName, String lastName, String phoneNumber, String nationalCode, String email, String password, String passwordConfirm
        authService.register(user, registerRequest.getPasswordConfirm());
        ResponseMessage responseMessage = ResponseMessage.withResponseData(userRegisterResponseDto, "user registered successfully", "info");
        return ResponseEntity.ok(responseMessage);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("You've been signed out!");
    }


}
