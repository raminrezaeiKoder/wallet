package com.digitalwallet.Exception;

import com.digitalwallet.messages.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({WithdrawWalletIsDeactivatedException.class , DepositWalletIsDeactivatedException.class})
    public ResponseEntity<ErrorMessage> WalletIsDeactivatedException(RuntimeException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.NOT_ACCEPTABLE.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_ACCEPTABLE);
    }


    @ExceptionHandler(WithdrawLowBalanceException.class)
    public ResponseEntity<ErrorMessage> WithdrawLowBalanceException(WithdrawLowBalanceException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.PAYMENT_REQUIRED.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.PAYMENT_REQUIRED);
    }

    @ExceptionHandler(SourceAndDestAreTheSame.class)
    public ResponseEntity<ErrorMessage> SourceAndDestAreTheSame(SourceAndDestAreTheSame ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.NOT_ACCEPTABLE.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler({WalletNotFoundException.class, UserNotFoundException.class, RuntimeException.class})
    public ResponseEntity<ErrorMessage> NotFoundException(Exception ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.NO_CONTENT.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
    }
}
