package com.digitalwallet.service;

import com.digitalwallet.Exception.UserNotFoundException;
import com.digitalwallet.Exception.WalletNotFoundException;
import com.digitalwallet.entity.User;
import com.digitalwallet.entity.Wallet;
import com.digitalwallet.generic.GenericRepository;
import com.digitalwallet.generic.GenericServiceImpl;
import com.digitalwallet.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletService extends GenericServiceImpl<Wallet, Long> {
    private final WalletRepository walletRepository;

    private final UserService userService;

    public WalletService(WalletRepository walletRepository, UserService userService) {
        this.walletRepository = walletRepository;
        this.userService = userService;
    }

    @Override
    protected GenericRepository getRepository() {

        return this.walletRepository;
    }

    public List<Wallet> findByActiveStatusFalse() {
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

    public void saveWalletWithItsUser(Wallet wallet, Long userId) throws UserNotFoundException {
        User user = userService.findBydId(userId).orElseThrow(() -> new UserNotFoundException());
        wallet.setWalletUser(user);
        walletRepository.save(wallet);
    }


    public void updateWalletWithItsUser(Wallet wallet, Long userId) throws UserNotFoundException, WalletNotFoundException {
        User user = userService.findBydId(userId).orElseThrow(() -> new UserNotFoundException());
        Wallet wallet2 = walletRepository.findById(wallet.getWalletId()).orElseThrow(WalletNotFoundException::new);
        wallet2.setWalletUser(user);
        walletRepository.save(wallet2);
    }
}
