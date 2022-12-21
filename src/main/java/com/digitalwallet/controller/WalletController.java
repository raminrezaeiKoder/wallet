package com.digitalwallet.controller;

import com.digitalwallet.Exception.UserNotFoundException;
import com.digitalwallet.Exception.WalletNotFoundException;
import com.digitalwallet.dto.request.WalletRequestDto;
import com.digitalwallet.dto.response.WalletResponseDto;
import com.digitalwallet.entity.Wallet;
import com.digitalwallet.mapper.request.WalletRequestMapper;
import com.digitalwallet.mapper.response.WalletResponseMapper;
import com.digitalwallet.messages.ResponseMessage;
import com.digitalwallet.service.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("wallets")
public class WalletController {
    private final WalletService walletService;

    private final WalletRequestMapper walletRequestMapper;
    private final WalletResponseMapper walletResponseMapper;

    public WalletController(WalletService walletService, WalletRequestMapper walletRequestMapper, WalletResponseMapper walletResponseMapper) {
        this.walletService = walletService;
        this.walletRequestMapper = walletRequestMapper;
        this.walletResponseMapper = walletResponseMapper;
    }

    @PostMapping
    public ResponseEntity<ResponseMessage<?>> save(@RequestBody WalletRequestDto walletRequestDto, HttpServletResponse httpServletResponse) throws UserNotFoundException {
        Wallet wallet = walletRequestMapper.toBaseEntity(walletRequestDto);
        walletService.saveWalletWithItsUser(wallet, walletRequestDto.getUserId());
        WalletResponseDto walletResponseDto = walletResponseMapper.toBaseDto(wallet);
        walletResponseDto.setNationalCode(wallet.getWalletUser().getNationalCode());
        ResponseMessage responseMessage = ResponseMessage.withResponseData(wallet, "wallet Created Successfully", "info");

        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<WalletResponseDto>> findAll(){
        List<Wallet> wallets = (List)walletService.findAll() ;
        return ResponseEntity.ok((List) walletResponseMapper.toBaseDtoList(wallets));
    }

    @GetMapping("/walletCode/{walletCode}")
    public ResponseEntity<WalletResponseDto> findByWalletCode(@PathVariable("walletCode") String walletCode) throws WalletNotFoundException {


        return ResponseEntity.ok(walletResponseMapper.toBaseDto(walletService.findByWalletCode(walletCode).orElseThrow(WalletNotFoundException::new)));
    }

    @GetMapping("/{walletId}")
    public ResponseEntity<WalletResponseDto> findById(@PathVariable("walletId") Long walletId) throws WalletNotFoundException {
        return ResponseEntity.ok(walletResponseMapper.toBaseDto(walletService.findBydId(walletId).orElseThrow(WalletNotFoundException::new)));
    }
}
