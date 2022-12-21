package com.digitalwallet.controller;

import com.digitalwallet.Exception.ExceptionStatus;
import com.digitalwallet.Exception.WithdrawLowBalanceException;
import com.digitalwallet.Exception.WithdrawWalletIsDeactivatedException;
import com.digitalwallet.dto.request.TransactionRequestDto;
import com.digitalwallet.dto.response.TransactionResponseDto;
import com.digitalwallet.entity.Transaction;
import com.digitalwallet.entity.TransactionStatus;
import com.digitalwallet.entity.TransactionType;
import com.digitalwallet.entity.Wallet;
import com.digitalwallet.mapper.request.TransactionRequestMapper;
import com.digitalwallet.mapper.response.TransactionResponseMapper;
import com.digitalwallet.service.TransactionService;
import com.digitalwallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    @Autowired
    private TransactionRequestMapper transactionRequestMapper;

    @Autowired
    private TransactionResponseMapper transactionResponseMapper;

    @Autowired
    private WalletService walletService;


    @PostMapping("/withdraw")
    public ResponseEntity<TransactionResponseDto> withDraw(@RequestBody TransactionRequestDto transactionRequestDto) {
        Transaction transaction = transactionService.withdraw(transactionRequestDto , TransactionType.WITHDRAW);
        TransactionResponseDto transactionResponseDto = transactionResponseMapper.toBaseDto(transaction);
       // transactionResponseDto.setSourceName(transaction.getTransactionUser().getName());
       // transactionResponseDto.setSourceLastName(transaction.getTransactionUser().getLastName());
        return new ResponseEntity<>(transactionResponseDto, HttpStatus.OK);
    }


    @PostMapping("/deposit")
    public ResponseEntity<TransactionResponseDto> Deposit(@RequestBody TransactionRequestDto transactionRequestDto){
        Transaction transaction = transactionService.deposit(transactionRequestDto , TransactionType.DEPOSIT) ;
        TransactionResponseDto transactionResponseDto = transactionResponseMapper.toBaseDto(transaction);
        //transactionResponseDto.setDestinationWalletCode(transaction.getWallet().getWalletCode());
        transactionResponseDto.setDestinationUserName(transaction.getTransactionUser().getName());
        transactionResponseDto.setDestinationUserLastName(transaction.getTransactionUser().getLastName());
        return new ResponseEntity<>(transactionResponseDto, HttpStatus.OK);
    }



    @PostMapping("/transfer")
    public ResponseEntity<TransactionResponseDto> transfer(@RequestBody TransactionRequestDto transactionRequestDto){
        List<Transaction> transferTransactions = transactionService.transfer(transactionRequestDto) ;
        TransactionResponseDto transactionResponseDto = transactionResponseMapper.toBaseDto(transferTransactions.get(1));
        //transactionResponseDto.setSourceWalletCode(transactionRequestDto.getSourceWalletCode());
        transactionResponseDto.setDestinationUserName(transferTransactions.get(1).getTransactionUser().getName());
        transactionResponseDto.setDestinationUserLastName(transferTransactions.get(1).getTransactionUser().getLastName());
        transactionResponseDto.setTransactionType(TransactionType.TRANSFER);
        return new ResponseEntity<>(transactionResponseDto, HttpStatus.OK);
    }

@GetMapping
    public  ResponseEntity<List<TransactionResponseDto>> findAll(){
        return new ResponseEntity<>((List)transactionResponseMapper.toBaseDtoList(transactionService.findAll()),HttpStatus.OK) ;
}

@GetMapping("/transactionCode/{transactionCode}")
    public ResponseEntity<TransactionResponseDto> findByTransactionCode(@PathVariable("transactionCode") String transactionCode){
        return new ResponseEntity<>(transactionResponseMapper.toBaseDto(transactionService.findByTransactionCode(transactionCode).get()),HttpStatus.OK);
}

@GetMapping("{transactionId}")
    public ResponseEntity<TransactionResponseDto> findById(@PathVariable("transactionId") Long transactionId){
        return new ResponseEntity<>(transactionResponseMapper.toBaseDto(transactionService.findBydId(transactionId).get()),HttpStatus.OK);
}
}
