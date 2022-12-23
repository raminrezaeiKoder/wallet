package com.digitalwallet.service;

import com.digitalwallet.Exception.UserNotFoundException;
import com.digitalwallet.entity.User;
import com.digitalwallet.utils.Token;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class AuthService {


    private final UserService userService;


    private final PasswordEncoder passwordEncoder;

    public AuthService(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;

    }


    public Boolean checkAuthenticationByUserId(Long userId) throws UserNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userService.findById(userId).orElseThrow(UserNotFoundException::new);
        if (userDetails.userName.equals(user.getUserName()))
            return true;
        return false;

    }

    public void register(User user, String passwordConfirm) {

        if (!Objects.equals(user.getPassword(), passwordConfirm)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "password and repeat not matched");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);

    }

    public void register(String userName, String firstName, String lastName, String phoneNumber, String nationalCode, String email, String password, String passwordConfirm) {
        if (!Objects.equals(password, passwordConfirm)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "password and repeat not matched");
        }
        User user = new User();
        user.setUserName(userName);
        user.setName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setNationalCode(nationalCode);
        user.setPhoneNumber(phoneNumber);
        userService.save(user);
    }

    public Token login(String userName, String password) {
        User user = userService.findByUserName(userName).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "username or password is wrong"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username or password is wrong");
        }

        return Token.of(userName, 10L, "shjbashjb");
    }


}
