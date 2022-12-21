package com.digitalwallet.dto.request;

import com.digitalwallet.generic.GenericDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class WalletRequestDto extends GenericDto {

    private String walletCode ;
    private Long balance ;
    private Boolean activeStatus ;
    private Long userId ;

}
