package com.digitalwallet.Exception;

public class DepositWalletIsDeactivatedException extends RuntimeException {

    public DepositWalletIsDeactivatedException(String message) {
        super(message);
    }
}
