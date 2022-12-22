package com.digitalwallet.dto.request;

import com.digitalwallet.generic.GenericDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequestDto extends GenericDto {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;


}
