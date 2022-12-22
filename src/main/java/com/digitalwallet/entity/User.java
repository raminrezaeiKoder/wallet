package com.digitalwallet.entity;

import com.digitalwallet.generic.GenericEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user", schema = "digital_wallet")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User extends GenericEntity {


    @Column(name = "username", unique = true, nullable = false, length = 16)
    private String userName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "name", nullable = false, length = 20)
    private String name;
    @Column(name = "email", nullable = true, length = 50, unique = true)
    private String email;
    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;
    @Column(name = "phone_number", nullable = false, length = 12, unique = true)
    private String phoneNumber;
    @Column(name = "national_code", nullable = false, length = 10, unique = true)
    private String nationalCode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "walletUser")
    private List<Wallet> walletList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transactionUser")
    private List<Transaction> transactionList = new ArrayList<>();
    @Cascade({
            org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.MERGE,
            org.hibernate.annotations.CascadeType.PERSIST
    })
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
}
