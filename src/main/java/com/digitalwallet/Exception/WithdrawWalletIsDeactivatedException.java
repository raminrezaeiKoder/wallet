package com.digitalwallet.Exception;

public class WithdrawWalletIsDeactivatedException extends RuntimeException{

    public WithdrawWalletIsDeactivatedException(String message) {
        super(message);
    }
}
