package com.digitalwallet.service;

import com.digitalwallet.entity.User;
import com.digitalwallet.entity.Wallet;
import com.digitalwallet.generic.GenericRepository;
import com.digitalwallet.generic.GenericServiceImpl;
import com.digitalwallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletService extends GenericServiceImpl<Wallet, Long> {
   @Autowired
    private WalletRepository walletRepository ;

   @Autowired
   private UserService userService ;
    @Override
    protected GenericRepository getRepository() {

        return this.walletRepository ;
    }


    public List<Wallet> findByActiveStatusFalse(){
        return walletRepository.findByActiveStatusFalse();
    }

    public List<Wallet> findByActiveServiceTrue(){
        return walletRepository.findByActiveStatusTrue() ;
    }

    public List<Wallet> findByBalance(Long balance){
        return walletRepository.findByBalance(balance) ;
    }


    public Optional<Wallet> findByWalletCode(String walletCode){
        return walletRepository.findByWalletCode(walletCode) ;
    }


    //save a wallet into database with its userId
    public void saveWalletWithItsUser(Wallet wallet , Long userId ){
        User user = userService.findBydId(userId).get() ;
        wallet.setWalletUser(user);
        super.save(wallet);
    }


}
