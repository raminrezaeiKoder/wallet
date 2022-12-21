package com.digitalwallet.entity;

import com.digitalwallet.generic.GenericEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "transaction", schema = "digital_wallet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction extends GenericEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "transaction_code", unique = true, length = 15)
    private String transactionCode;
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_status")
    private TransactionStatus transactionStatus;


    @Column(name = "src_wallet_code", nullable = true, unique = false, length = 16)
    private String sourceWalletCode;

    @Column(name = "dest_wallet_code", nullable = true, unique = false, length = 16)
    private String destinationWalletCode;


    @Column(name = "amount", nullable = false, length = 20)
    private Long amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User transactionUser;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

}
