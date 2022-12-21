package com.digitalwallet.mapper.request;

import com.digitalwallet.dto.request.WalletRequestDto;
import com.digitalwallet.entity.Wallet;
import com.digitalwallet.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper
public interface WalletRequestMapper extends GenericMapper<Wallet, WalletRequestDto> {
}
