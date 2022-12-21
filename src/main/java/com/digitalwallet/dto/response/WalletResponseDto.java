package com.digitalwallet.dto.response;

import com.digitalwallet.generic.GenericDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class WalletResponseDto extends GenericDto {

    private String walletCode;
    private Boolean activeStatus ;

    private String nationalCode ;

    public String getWalletCode() {
        return walletCode;
    }

}
