package com.digitalwallet.generic;

import java.io.Serializable;
import java.util.Optional;

public abstract class GenericServiceImpl<T extends GenericEntity, ID extends Serializable> implements GenericService<T, ID> {

    protected abstract GenericRepository getRepository();

    @Override
    public Optional<T> findBydId(ID id) {
        return getRepository().findById(id);
    }

    @Override
    public Iterable<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    public void save(T t) {
        getRepository().save(t);
    }

    @Override
    public void deleteById(ID id) {
        getRepository().deleteById(id);
    }

    @Override
    public void deleteAll() {
        getRepository().deleteAll();
    }
}
