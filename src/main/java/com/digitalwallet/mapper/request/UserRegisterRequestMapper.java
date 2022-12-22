package com.digitalwallet.mapper.request;

import com.digitalwallet.dto.request.UserRegisterRequestDto;
import com.digitalwallet.entity.User;
import com.digitalwallet.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper
public interface UserRegisterRequestMapper extends GenericMapper<User, UserRegisterRequestDto> {
}
