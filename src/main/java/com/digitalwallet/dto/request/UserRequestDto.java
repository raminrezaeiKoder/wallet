package com.digitalwallet.dto.request;

import com.digitalwallet.generic.GenericDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserRequestDto extends GenericDto {

    private String name ;
    private String lastName ;
    private String email ;
    private String phoneNumber ;
    private String nationalCode;
    //private List<WalletRequestDto> walletList = new ArrayList<>(); //todo it can be done later
   // private List<Transaction> transactionList = new ArrayList<>();


}
