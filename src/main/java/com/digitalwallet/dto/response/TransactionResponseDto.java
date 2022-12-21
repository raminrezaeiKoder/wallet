package com.digitalwallet.dto.response;

import com.digitalwallet.entity.TransactionStatus;
import com.digitalwallet.entity.TransactionType;
import com.digitalwallet.generic.GenericDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionResponseDto extends GenericDto {

    private String sourceWalletCode ;
    private String destinationWalletCode;
    private Long amount ;
    private String destinationUserName ;
    private String destinationUserLastName ;
    private TransactionType transactionType ;
    private TransactionStatus transactionStatus ;

    public String getSourceWalletCode() {
        return sourceWalletCode;
    }

    public void setSourceWalletCode(String sourceWalletCode) {
        this.sourceWalletCode = sourceWalletCode;
    }


}
