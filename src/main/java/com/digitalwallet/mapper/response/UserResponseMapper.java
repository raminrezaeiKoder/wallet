package com.digitalwallet.mapper.response;

import com.digitalwallet.dto.response.UserResponseDto;
import com.digitalwallet.entity.User;
import com.digitalwallet.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper
public interface UserResponseMapper extends GenericMapper<User, UserResponseDto> {
}
