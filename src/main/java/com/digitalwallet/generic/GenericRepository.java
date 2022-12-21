package com.digitalwallet.generic;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
@NoRepositoryBean
public interface GenericRepository<T extends GenericEntity , ID extends Serializable> extends PagingAndSortingRepository<T , ID> {



}
