package com.digitalwallet.mapper.request;

import com.digitalwallet.dto.request.UserRequestDto;
import com.digitalwallet.entity.User;
import com.digitalwallet.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper
public interface UserRequestMapper extends GenericMapper<User, UserRequestDto> {
}
