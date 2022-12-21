package com.digitalwallet.entity;

import com.digitalwallet.generic.GenericEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.persistence.*;

@Entity
@Table(name="transaction" , schema = "digital_wallet")
public class Transaction extends GenericEntity{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId ;

    @Column(name="transaction_code" , unique = true , length = 15 )
    private String transactionCode;
    @Enumerated(EnumType.STRING)
    @Column(name="transaction_type")
    private TransactionType transactionType ;
    @Enumerated(EnumType.STRING)
    @Column(name="transaction_status")
    private TransactionStatus transactionStatus ;


    @Column(name="src_wallet_code" , nullable = true , unique = false , length = 16)
    private String sourceWalletCode ;

    @Column(name="dest_wallet_code" , nullable = true , unique = false , length = 16)
    private String destinationWalletCode;


    @Column(name="amount" , nullable = false  , length = 20)
    private Long amount ;




    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id" )
    private User transactionUser ;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id" )
    private Wallet wallet ;
    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
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

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

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

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public User getTransactionUser() {
        return transactionUser;
    }

    public void setTransactionUser(User transactionUser) {
        this.transactionUser = transactionUser;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
}
