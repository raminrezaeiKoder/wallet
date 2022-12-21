package com.digitalwallet.utils;

import com.digitalwallet.entity.Transaction;

public abstract class TransactionUtils {


    public static String generateTransactionCode(Transaction transaction){
        return String.format("x");//todo make a correct code
    }

}
