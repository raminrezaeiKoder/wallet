package com.digitalwallet.entity;

import com.digitalwallet.generic.GenericEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "digital_wallet")
public class User extends GenericEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username", nullable = false, length = 20)
    private String name;
    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;
    @Column(name = "email", nullable = true, length = 40)
    private String email;
    @Column(name = "phone_number", nullable = false, length = 12 , unique = true)
    private String phoneNumber;

    @Column(name="national_code" , nullable = false , length = 10 , unique = true)
    private String nationalCode ;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "walletUser")
    private List<Wallet> walletList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "transactionUser")
    private List<Transaction> transactionList = new ArrayList<>();



    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public List<Wallet> getWalletList() {
        return walletList;
    }

    public void setWalletList(List<Wallet> walletList) {
        this.walletList = walletList;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUserId(), user.getUserId()) &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getLastName(), user.getLastName()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getPhoneNumber(), user.getPhoneNumber()) &&
                Objects.equals(getNationalCode(), user.getNationalCode()) &&
                Objects.equals(getWalletList(), user.getWalletList()) &&
                Objects.equals(getTransactionList(), user.getTransactionList());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getUserId(), getName(), getLastName(), getEmail(), getPhoneNumber(), getNationalCode(), getWalletList(), getTransactionList());
    }
}
