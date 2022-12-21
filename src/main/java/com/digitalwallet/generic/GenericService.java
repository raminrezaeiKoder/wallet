package com.digitalwallet.generic;

import java.io.Serializable;
import java.util.Optional;

public interface GenericService<T extends GenericEntity , ID extends Serializable> {


    public Optional<T> findBydId(ID id) ;
    public Iterable<T> findAll() ;
    public void save(T t) ;
    public void deleteById(ID id) ;
    public void deleteAll() ;


}
