package com.digitalwallet.dto.request;

import com.digitalwallet.generic.GenericDto;
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
    private String name;
    private String lastName;


    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    private String phoneNumber;
    @NotBlank
    @Size(min = 1, max = 10)
    private String nationalCode;
    //private List<WalletRequestDto> walletList = new ArrayList<>(); //todo it can be done later
    // private List<Transaction> transactionList = new ArrayList<>();


    private Set<String> role;
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;


}
