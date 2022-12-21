package com.digitalwallet.repository;

import com.digitalwallet.entity.User;
import com.digitalwallet.generic.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository  extends GenericRepository<User,Long>{
    public List<User> findByNameAndLastName(String name , String lastName);
    public List<User> findByName(String name) ;
    public List<User> findByLastName(String lastName) ;
    public List<User> findByPhoneNumber(String phoneNumber) ;
    public List<User> findByPhoneNumberStartingWith(String prefix);
    public List<User> findByEmail(String email ) ;
    public Optional<User> findByNationalCode(String nationalCode);

}
