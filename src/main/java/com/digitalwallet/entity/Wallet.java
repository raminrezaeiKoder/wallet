package com.digitalwallet.entity;

import com.digitalwallet.generic.GenericEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wallet", schema = "digital_wallet")
public class Wallet extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wallet_id")
    private Long walletId;

    @Column(name = "wallet_code", nullable = false, length = 16, unique = true)
    private String walletCode;
    @Column(name = "balance", nullable = false)
    private Long balance;

//    @Column(name = "wallet_name", nullable = false, length = 15)
//    private String name;

    @Column(nullable = false)
    private boolean activeStatus = true;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id" , nullable = true )
    private User walletUser ;


    @OneToMany(cascade = CascadeType.ALL , mappedBy ="wallet")
    private List<Transaction> transactionList = new ArrayList<>();
    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    public String getWalletCode() {
        return walletCode;
    }

    public void setWalletCode(String walletCode) {
        this.walletCode = walletCode;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public boolean isActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public User getWalletUser() {
        return walletUser;
    }

    public void setWalletUser(User walletUser) {
        this.walletUser = walletUser;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
}
