package com.digitalwallet.controller;

import com.digitalwallet.dto.request.UserRequestDto;
import com.digitalwallet.dto.response.UserResponseDto;
import com.digitalwallet.dto.response.WalletResponseDto;
import com.digitalwallet.entity.User;
import com.digitalwallet.mapper.request.UserRequestMapper;
import com.digitalwallet.mapper.response.UserResponseMapper;
import com.digitalwallet.mapper.response.WalletResponseMapper;
import com.digitalwallet.service.UserService;
import com.digitalwallet.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    private final WalletResponseMapper walletResponseMapper;
    private final WalletService walletService;

    private final UserRequestMapper userRequestMapper;

    private final UserResponseMapper userResponseMapper;

    public UserController(UserService userService, WalletResponseMapper walletResponseMapper, WalletService walletService, UserRequestMapper userRequestMapper, UserResponseMapper userResponseMapper) {
        this.userService = userService;
        this.walletResponseMapper = walletResponseMapper;
        this.walletService = walletService;
        this.userRequestMapper = userRequestMapper;
        this.userResponseMapper = userResponseMapper;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> saveUser(@RequestBody UserRequestDto userRequestDto, HttpServletResponse httpServletResponse) {
        User user = userRequestMapper.toBaseEntity(userRequestDto);
        userService.save(user);
        return ResponseEntity.ok(userResponseMapper.toBaseDto(userRequestMapper.toBaseEntity(userRequestDto)));
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable("userId") Long userId) {
        User user = userService.findBydId(userId).get();
        return ResponseEntity.ok(userResponseMapper.toBaseDto(user));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll(){

        return ResponseEntity.ok((List)userResponseMapper.toBaseDtoList(userService.findAll()));

    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody UserRequestDto userRequestDto){
        User user = userRequestMapper.toBaseEntity(userRequestDto);
        userService.save(user);
        return ResponseEntity.ok(userResponseMapper.toBaseDto(userRequestMapper.toBaseEntity(userRequestDto)));
    }

    @GetMapping("name/{userName}")
    public ResponseEntity<List<UserResponseDto>> findByName(@PathVariable("userName") String name){
        return ResponseEntity.ok((List)userResponseMapper.toBaseDtoList(userService.findByName(name)));
    }

    @GetMapping("/showWallets/{nationalCode}")
    public ResponseEntity<List<WalletResponseDto>> getWallets(@PathVariable("nationalCOde") String nationalCode){
        User user = userService.findByNationalCode(nationalCode).get() ;
        return ResponseEntity.ok((List)walletResponseMapper.toBaseDtoList(user.getWalletList()));
    }
}
