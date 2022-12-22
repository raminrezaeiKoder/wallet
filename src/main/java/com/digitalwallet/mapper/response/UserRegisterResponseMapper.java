package com.digitalwallet.mapper.response;

import com.digitalwallet.dto.response.UserRegisterResponseDto;
import com.digitalwallet.entity.User;
import com.digitalwallet.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper
public interface UserRegisterResponseMapper extends GenericMapper<User, UserRegisterResponseDto> {
}
