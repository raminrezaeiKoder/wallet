package com.digitalwallet.entity;

import com.digitalwallet.generic.GenericEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "role", schema = "digital_wallet")
@NoArgsConstructor
@AllArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class Role extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Long roleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_type")
    private RoleType roleType;


    @Column(name = "role_detail", nullable = true, length = 20)

    private String roleDetail;


    public Role(RoleType roleType, String roleDetail) {
        this.roleDetail = roleDetail;
        this.roleType = roleType;
    }

    public Role(RoleType roleType) {
        this.roleType = roleType;
    }
}
