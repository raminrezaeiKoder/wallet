package com.digitalwallet.service;

import com.digitalwallet.Exception.*;
import com.digitalwallet.dto.request.TransactionRequestDto;
import com.digitalwallet.entity.Transaction;
import com.digitalwallet.entity.TransactionStatus;
import com.digitalwallet.entity.TransactionType;
import com.digitalwallet.entity.Wallet;
import com.digitalwallet.generic.GenericRepository;
import com.digitalwallet.generic.GenericServiceImpl;
import com.digitalwallet.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService extends GenericServiceImpl<Transaction, Long> {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private WalletService walletService;

    @Override
    protected GenericRepository getRepository() {
        return this.transactionRepository;
    }

    public List<Transaction> findByTransactionType(TransactionType transactionType) {
        return transactionRepository.findByTransactionType(transactionType);
    }

    public List<Transaction> findByTransactionStatus(TransactionStatus transactionStatus) {
        return transactionRepository.findByTransactionStatus(transactionStatus);
    }

    public Optional<Transaction> findByTransactionCode(String transactionCode) {
        return transactionRepository.findByTransactionCode(transactionCode);
    }

    /*  initializing a transaction :
     *   1 . setting transaction type
     *   2 . setting its wallet
     *   3 . setting source and destination wallet code for it
     * */
    public List<Transaction> findByAmount(Long amount) {
        return transactionRepository.findByAmount(amount);
    }

    public Transaction initializeTransaction(Wallet wallet, TransactionRequestDto transactionRequestDto, TransactionType transactionType) {
        Transaction transaction = new Transaction();
        transaction.setTransactionType(transactionType);
        transaction.setWallet(wallet);
        transaction.setTransactionUser(wallet.getWalletUser());
        transaction.setAmount(transactionRequestDto.getAmount());
        //set source wallet code to transaction
        transaction.setSourceWalletCode(transactionRequestDto.getSourceWalletCode());
        //setting the destination wallet code for transaction
        transaction.setDestinationWalletCode(transactionRequestDto.getDestinationWalletCode());
        return transaction;
    }


    /*
     * 1.check whether wallet is active or not
     * 2.check the wallet balance
     * 3.check if it is a part of wallet to wallet transfer operation
     * 4.do the withdraw and update the walelt
     * 5.(a transaction will be created in every state with different status )
     * */
    public Transaction withdraw(TransactionRequestDto transactionRequestDto, TransactionType transactionType) throws WithdrawWalletIsDeactivatedException, WithdrawLowBalanceException {
        //extracting source wallet code from request
        String sourceWalletCode = transactionRequestDto.getSourceWalletCode();
        //find source wallet using wallet code
        Wallet wallet = walletService.findByWalletCode(sourceWalletCode).get();


        //initializing a transaction fot withdraw operation
        Transaction transaction = this.initializeTransaction(wallet, transactionRequestDto, transactionType);
        //checking whether source wallet is active or not ?
        if (wallet.isActiveStatus()) {


            //checking whether if there exists enough money inside source wallet
            if (wallet.getBalance() >= transaction.getAmount()) {
                //decrease the source wallet balance
                wallet.setBalance(wallet.getBalance() - transaction.getAmount());
                //update the source wallet with new balance
                walletService.save(wallet);

                //check if this operation is a part of wallet to wallet transaction
                if (transactionType == TransactionType.TRANSFER_WITHDRAW) {
                    //set the wallet to wallet withdraw operation waiting for deposit operation to be succeeded
                    transaction.setTransactionStatus(TransactionStatus.WAITING_FOR_DEPOSIT);
                    //setting withdraw operation was successful
                } else transaction.setTransactionStatus(TransactionStatus.SUCCESS);

                //adding the transaction to database  and return it
                this.save(transaction);
                return transaction;
            } else {
                //according to low wallet balance , the transaction is a failed transaction during transaction processing
                transaction.setTransactionStatus(TransactionStatus.FAILED_IN_BEING_PROCESSED);
                //adding the unsuccessful transaction to database
                this.save(transaction);
                //return the low balance exception
                throw new WithdrawLowBalanceException(ExceptionStatus.LOW_BALANCE.toString());
            }
        } else {
            //according to a deactivated wallet , transaction is a failed transaction with failed in intitalization status
            transaction.setTransactionStatus(TransactionStatus.FAILED_IN_INIT);
            //add the unsuccessful transaction to database
            this.save(transaction);
            //return the corresponding exception
            throw new WithdrawWalletIsDeactivatedException(ExceptionStatus.SRC_WALLET_IS_DEACTIVATED.toString());
        }
    }


    /*
     * 1.check whether wallet is active or not
     *
     * 2.check if it is a part of wallet to wallet transfer operation
     * 3.do the deposit and update the walelt
     * 4.(a transaction will be created in every state with different status )
     * */
    public Transaction deposit(TransactionRequestDto transactionRequestDto, TransactionType transactionType) {
        String destinationWalletCode = transactionRequestDto.getDestinationWalletCode();

        //find the destination wallet using wallet code
        Wallet wallet = walletService.findByWalletCode(destinationWalletCode).get();
        Transaction transaction = this.initializeTransaction(wallet, transactionRequestDto, transactionType);
        //checking whether wallet is active or not
        if (wallet.isActiveStatus()) {

            //adding the deposit amount to destination wallet
            wallet.setBalance(wallet.getBalance() + transaction.getAmount());

            //update destination  wallet with new balance
            walletService.save(wallet);
            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
            //save the transaction to database and return
            this.save(transaction);
            return transaction;
        } else {
            //checking whether it is wallet to wallet transfer deposit or not ?
            if (transactionType == TransactionType.TRANSFER_DEPOSIT) {

                //according to a failed deposite the updated wallet balanced should be rolled back to its first balance
                wallet = walletService.findByWalletCode(transactionRequestDto.getSourceWalletCode()).get();
                //setting sort wallet balance
                wallet.setBalance(wallet.getBalance() + transactionRequestDto.getAmount());
                //update source wallet
                walletService.save(wallet);
            }

            //according to a failed deposit transaction failed during initialization
            transaction.setTransactionStatus(TransactionStatus.FAILED_IN_INIT);
            //save the failed transaction
            this.save(transaction);
            //throwing the corresponding destination wallet is not active exception
            throw new DepositWalletIsDeactivatedException(ExceptionStatus.DEST_WALLET_IS_DEACTIVATED.toString());
        }
    }


    /*
     * 1. check the source wallet code and destination wallet code for not being equal
     * 2. withdraw , with transaction type -> transfer_withdraw
     * 3. deposite , with transaction type -> transfer_withdraw
     * 4. (save a transaction if there is a successful transfer or not)
     * 5. return both of the withdraw and deposit transactions as a List
     * */

    public List<Transaction> transfer(TransactionRequestDto transactionRequestDto) {
        //check whether the source wallet code is equal to destination wallet code , if yes the operation is failed
        if (transactionRequestDto.getSourceWalletCode().equals(transactionRequestDto.getDestinationWalletCode())) {
            throw new SourceAndDestAreTheSame(ExceptionStatus.SAME_SRC_AND_DEST.toString());
        } else {
            List<Transaction> transactionList = new ArrayList<>();
            Transaction withdrawTransaction = this.withdraw(transactionRequestDto, TransactionType.TRANSFER_WITHDRAW);
            Transaction depositTransaction = this.deposit(transactionRequestDto, TransactionType.TRANSFER_DEPOSIT);
            transactionList.add(withdrawTransaction);
            transactionList.add(depositTransaction);
            return transactionList;
        }
    }

    @Override
    public void save(Transaction transaction) {
        //transaction.setTransactionCode(TransactionUtils.generateTransactionCode(transaction));
        super.save(transaction);
    }
}
