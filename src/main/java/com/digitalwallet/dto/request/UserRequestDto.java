package com.digitalwallet.dto.request;

import com.digitalwallet.entity.Transaction;
import com.digitalwallet.entity.Wallet;
import com.digitalwallet.generic.GenericDto;

import java.util.ArrayList;
import java.util.List;

public class UserRequestDto extends GenericDto {

    private String name ;
    private String lastName ;
    private String email ;
    private String phoneNumber ;
    private String nationalCode;
    //private List<WalletRequestDto> walletList = new ArrayList<>(); //todo it can be done later
   // private List<Transaction> transactionList = new ArrayList<>();


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

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
