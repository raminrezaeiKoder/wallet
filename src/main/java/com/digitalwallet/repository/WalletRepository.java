package com.digitalwallet.repository;

import com.digitalwallet.entity.Wallet;
import com.digitalwallet.generic.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface WalletRepository extends GenericRepository<Wallet, Long> {


    public List<Wallet> findByActiveStatusTrue();
    public List<Wallet> findByActiveStatusFalse() ;
    public Optional<Wallet> findByWalletCode(String walletCode) ;
    public List<Wallet> findByBalance(Long balance) ;

}
