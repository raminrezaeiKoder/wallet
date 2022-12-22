package com.digitalwallet.mapper.request;

import com.digitalwallet.dto.request.UserLoginRequestDto;
import com.digitalwallet.entity.User;
import com.digitalwallet.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper
public interface UserLoginRequestMapper extends GenericMapper<User, UserLoginRequestDto> {
}
