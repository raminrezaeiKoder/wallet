package com.digitalwallet.service;

import com.digitalwallet.entity.User;
import com.digitalwallet.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class AuthService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(String userName, String firstName, String lastName, String phoneNumber, String nationalCode, String email, String password, String passwordConfirm) {
        if (!Objects.equals(password, passwordConfirm)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "password and repeat not matched");
        }
        User user = new User();
        user.setUserName(userName);
        user.setName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setNationalCode(nationalCode);
        user.setPhoneNumber(phoneNumber);
        return userRepository.save(user);
    }

    public Token login(String userName, String password) {
        User user = userRepository.findByUserName(userName).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "username or password is wrong"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username or password is wrong");
        }

        return Token.of(userName, 10L, "shjbashjb");
    }


}
