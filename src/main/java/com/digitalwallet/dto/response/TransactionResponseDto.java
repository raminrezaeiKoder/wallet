package com.digitalwallet.dto.response;

import com.digitalwallet.entity.TransactionStatus;
import com.digitalwallet.entity.TransactionType;
import com.digitalwallet.generic.GenericDto;

public class TransactionResponseDto extends GenericDto {

    private String sourceWalletCode ;
    private String destinationWalletCode;
    private Long amount ;
   // private String sourceName ;
  //  private String sourceLastName ;
    private String destinationUserName ;
    private String destinationUserLastName ;
    private TransactionType transactionType ;
    private TransactionStatus transactionStatus ;

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

    public String getDestinationUserName() {
        return destinationUserName;
    }

    public void setDestinationUserName(String destinationUserName) {
        this.destinationUserName = destinationUserName;
    }

    public String getDestinationUserLastName() {
        return destinationUserLastName;
    }

    public void setDestinationUserLastName(String destinationUserLastName) {
        this.destinationUserLastName = destinationUserLastName;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
}
