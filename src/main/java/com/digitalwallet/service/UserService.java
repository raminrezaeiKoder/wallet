package com.digitalwallet.service;

import com.digitalwallet.entity.User;
import com.digitalwallet.generic.GenericRepository;
import com.digitalwallet.generic.GenericServiceImpl;
import com.digitalwallet.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService extends GenericServiceImpl<User, Long> {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected GenericRepository getRepository() {
        return this.userRepository;
    }

    public List<User> findByNameAndLastName(String name, String lastName) {
        return userRepository.findByNameAndLastName(name, lastName);
    }

    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }


    public List<User> findByLastName(String lastName) {
        return userRepository.findByLastName(lastName);
    }

    public List<User> findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public List<User> findByPhoneNumberStartingWith(String prefix) {
        return this.userRepository.findByPhoneNumberStartingWith(prefix);
    }


    @Override
    public void save(User user) {
        super.save(user);
    }

    public Optional<User> findByNationalCode(String naitonalCode) {
        return userRepository.findByNationalCode(naitonalCode);
    }
}
