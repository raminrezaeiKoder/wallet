package com.digitalwallet.dto.request;

import com.digitalwallet.generic.GenericDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionRequestDto extends GenericDto {

    //todo add validation for source wallet code
    private String sourceWalletCode ;
    //todo add validation for destination wallet code
    private String destinationWalletCode ;
    //private TransactionType transactionType ;
    private Long amount ;

}
