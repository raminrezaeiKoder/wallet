package com.digitalwallet.mapper.response;

import com.digitalwallet.dto.response.TransactionResponseDto;
import com.digitalwallet.entity.Transaction;
import com.digitalwallet.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper
public interface TransactionResponseMapper extends GenericMapper<Transaction, TransactionResponseDto> {
}
