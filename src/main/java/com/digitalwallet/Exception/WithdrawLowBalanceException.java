package com.digitalwallet.Exception;

public class WithdrawLowBalanceException extends RuntimeException {

    public WithdrawLowBalanceException(String message) {
        super(message);
    }
}
