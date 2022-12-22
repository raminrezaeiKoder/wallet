package com.digitalwallet.repository;

import com.digitalwallet.entity.Role;
import com.digitalwallet.entity.RoleType;
import com.digitalwallet.generic.GenericRepository;

import java.util.Optional;

public interface RoleRepository extends GenericRepository<Role, Long> {

    public Optional<Role> findByRoleType(RoleType roleType);
}
