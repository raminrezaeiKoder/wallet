package com.digitalwallet.controller;

import com.digitalwallet.Exception.UserNotFoundException;
import com.digitalwallet.dto.request.UserRequestDto;
import com.digitalwallet.dto.response.UserResponseDto;
import com.digitalwallet.dto.response.WalletResponseDto;
import com.digitalwallet.entity.User;
import com.digitalwallet.mapper.request.UserRequestMapper;
import com.digitalwallet.mapper.response.UserResponseMapper;
import com.digitalwallet.mapper.response.WalletResponseMapper;
import com.digitalwallet.messages.ResponseMessage;
import com.digitalwallet.service.UserService;
import com.digitalwallet.service.WalletService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ResponseMessage<?>> saveUser(@RequestBody UserRequestDto userRequestDto, HttpServletResponse httpServletResponse) {
        User user = userRequestMapper.toBaseEntity(userRequestDto);
        userService.save(user);

        ResponseMessage responseMessage = ResponseMessage.withResponseData(userResponseMapper.toBaseDto(userRequestMapper.toBaseEntity(userRequestDto)), "user Created Successfully", "info");

        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResponseMessage<?>> findById(@PathVariable("userId") Long userId) throws UserNotFoundException {
        User user = userService.findBydId(userId).orElseThrow(() -> new UserNotFoundException());


        ResponseMessage responseMessage = ResponseMessage.withResponseData(userResponseMapper.toBaseDto(user), "find successfully", "info");

        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll() {

        return ResponseEntity.ok((List) userResponseMapper.toBaseDtoList(userService.findAll()));

    }

    @PutMapping
    public ResponseEntity<ResponseMessage<?>> update(@RequestBody UserRequestDto userRequestDto) {
        User user = userRequestMapper.toBaseEntity(userRequestDto);
        userService.save(user);


        ResponseMessage responseMessage = ResponseMessage.withResponseData(userResponseMapper.toBaseDto(userRequestMapper.toBaseEntity(userRequestDto)), "updated successfully", "info");

        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.ACCEPTED);
    }

    @GetMapping("name/{userName}")
    public ResponseEntity<List<UserResponseDto>> findByName(@PathVariable("userName") String name) throws UserNotFoundException {
        return ResponseEntity.ok((List) userResponseMapper.toBaseDtoList(userService.findByName(name)));
    }

    @GetMapping("/showWallets/{nationalCode}")
    public ResponseEntity<List<WalletResponseDto>> getWallets(@PathVariable("nationalCode") String nationalCode) throws UserNotFoundException {
        User user = userService.findByNationalCode(nationalCode).orElseThrow(() -> new UserNotFoundException());
        return ResponseEntity.ok((List) walletResponseMapper.toBaseDtoList(user.getWalletList()));
    }
}
