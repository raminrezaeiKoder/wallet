package com.digitalwallet.repository;

import com.digitalwallet.entity.RefreshToken;
import com.digitalwallet.entity.User;
import com.digitalwallet.generic.GenericRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface RefreshTokenRepository extends GenericRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    @Modifying
    int deleteByUser(User user);
}
