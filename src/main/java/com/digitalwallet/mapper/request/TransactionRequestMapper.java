package com.digitalwallet.mapper.request;

import com.digitalwallet.dto.request.TransactionRequestDto;
import com.digitalwallet.entity.Transaction;
import com.digitalwallet.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper
public interface TransactionRequestMapper extends GenericMapper<Transaction, TransactionRequestDto> {

}
