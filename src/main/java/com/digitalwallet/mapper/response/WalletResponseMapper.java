package com.digitalwallet.mapper.response;

import com.digitalwallet.dto.response.WalletResponseDto;
import com.digitalwallet.entity.Wallet;
import com.digitalwallet.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper
public interface WalletResponseMapper extends GenericMapper<Wallet , WalletResponseDto> {
}
