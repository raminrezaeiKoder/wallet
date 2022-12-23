package com.digitalwallet.entity;

import com.digitalwallet.generic.GenericEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "refresh_token", schema = "digital_wallet")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken extends GenericEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, unique = true, length = 64)
    private String token;

    @Column(nullable = false)
    private Instant expiryDate;


}
