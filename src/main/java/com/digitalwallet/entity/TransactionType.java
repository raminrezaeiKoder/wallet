package com.digitalwallet.entity;

public enum TransactionType {
    WITHDRAW("WITHDRAW"), DEPOSIT("DEPOSIT"), TRANSFER_DEPOSIT("TRANSFER_DEPOSIT") , TRANSFER_WITHDRAW("TRANSFER_WITHDRAW") , TRANSFER("TRANSFER");
    private String transactionType;

    TransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
