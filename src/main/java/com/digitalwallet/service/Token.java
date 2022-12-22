package com.digitalwallet.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.var;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

public class Token {


    private String token;

    private Token(String token) {
        this.token = token;
    }

    public static Token of(String username, Long validityInMinutes, String secretKey) {

        var issueDate = Instant.now();
        var generated = Jwts.builder().claim("username", username)
                .setIssuedAt(Date.from(issueDate))
                .setExpiration(Date.from(issueDate.plus(validityInMinutes, ChronoUnit.MINUTES)))
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8)))
                .compact();

        return new Token(generated);
    }

    public String getToken() {
        return token;
    }
}
