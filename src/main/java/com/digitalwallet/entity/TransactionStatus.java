package com.digitalwallet.entity;

public enum TransactionStatus {
    INIT("INIT"),
    BEING_PROCESSED("BEING_PROCESSED"),
    SUCCESS("SUCCESS"),
    FAILED_IN_INIT("FAILED_IN_INITIALIZING"),
    FAILED_IN_BEING_PROCESSED("FAILED_IN_BEING_PROCESSED"),
    WAITING_FOR_DEPOSIT("WAITING_FOR_DEPOSIT");
    private String status;

    TransactionStatus(String status) {
        this.status = status;
    }
}
