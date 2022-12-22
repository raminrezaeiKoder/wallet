package com.digitalwallet.dto.response;

import com.digitalwallet.generic.GenericDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterResponseDto extends GenericDto {


    private String userName;
    private String name;
    private String lastName;


    private String email;
    private String phoneNumber;

    private String nationalCode;
}
