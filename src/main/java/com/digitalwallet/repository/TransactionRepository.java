package com.digitalwallet.repository;

import com.digitalwallet.entity.Transaction;
import com.digitalwallet.entity.TransactionStatus;
import com.digitalwallet.entity.TransactionType;
import com.digitalwallet.generic.GenericRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TransactionRepository extends GenericRepository<Transaction, Long> {

    //@Query("select t from Transaction t where lower(t.transactionType)= LOWER(:#{transactionType.toString()}) ")
    public List<Transaction> findByTransactionType(TransactionType transactionType);

    //@Query("select t from Transaction t where LOWER(t.transactionStatus)= LOWER(:#{transactionStatus.toString()})")
    public List<Transaction> findByTransactionStatus(TransactionStatus transactionStatus);


    public Optional<Transaction> findByTransactionCode(String transactionCode) ;
    public List<Transaction> findByAmount(Long amount);
}
