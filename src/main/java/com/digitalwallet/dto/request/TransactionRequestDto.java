package com.digitalwallet.dto.request;

import com.digitalwallet.entity.Transaction;
import com.digitalwallet.entity.TransactionType;
import com.digitalwallet.generic.GenericDto;

public class TransactionRequestDto extends GenericDto {

    //todo add validation for source wallet code
    private String sourceWalletCode ;
    //todo add validation for destination wallet code
    private String destinationWalletCode ;
    //private TransactionType transactionType ;
    private Long amount ;

    public String getSourceWalletCode() {
        return sourceWalletCode;
    }

    public void setSourceWalletCode(String sourceWalletCode) {
        this.sourceWalletCode = sourceWalletCode;
    }

    public String getDestinationWalletCode() {
        return destinationWalletCode;
    }

    public void setDestinationWalletCode(String destinationWalletCode) {
        this.destinationWalletCode = destinationWalletCode;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
