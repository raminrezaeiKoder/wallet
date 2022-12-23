package com.digitalwallet.dto.request;

import com.digitalwallet.generic.GenericDto;
import com.digitalwallet.validation.NationalCode;
import com.digitalwallet.validation.PhoneNumber;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserRegisterRequestDto extends GenericDto {
    private Long userId;

    @NotBlank
    @Size(max = 20)
    private String userName;
    @NotBlank
    @Size(min = 2, max = 20)
    private String name;
    @NotBlank
    @Size(min = 4, max = 20)
    private String lastName;


    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    @NotBlank
    @PhoneNumber
    private String phoneNumber;
    @NotBlank
    @NationalCode
    private String nationalCode;


    private Set<String> role;
    @NotBlank

    private String password;

    @NotBlank

    private String passwordConfirm;


}
