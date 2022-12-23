package com.digitalwallet.Exception;

public class RefreshTokenException extends RuntimeException {
    public RefreshTokenException() {
        super("refresh token has expired");
    }
}
