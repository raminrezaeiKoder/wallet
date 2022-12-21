package com.digitalwallet.entity;

import com.digitalwallet.generic.GenericEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wallet", schema = "digital_wallet")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Wallet extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wallet_id")
    private Long walletId;

    @Column(name = "wallet_code", nullable = false, length = 16, unique = true)
    private String walletCode;
    @Column(name = "balance", nullable = false)
    private Long balance;

    @Column(nullable = false)
    private boolean activeStatus = true;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id" , nullable = true )
    private User walletUser ;


    @OneToMany(cascade = CascadeType.ALL , mappedBy ="wallet")
    private List<Transaction> transactionList = new ArrayList<>();

}
