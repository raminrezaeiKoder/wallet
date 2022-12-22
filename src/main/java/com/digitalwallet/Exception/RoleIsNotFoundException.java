package com.digitalwallet.Exception;

public class RoleIsNotFoundException extends RuntimeException {

    public RoleIsNotFoundException() {
        super("Role not found");
    }
}
