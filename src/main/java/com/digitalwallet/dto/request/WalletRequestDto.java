package com.digitalwallet.dto.request;

import com.digitalwallet.generic.GenericDto;
import com.digitalwallet.validation.CardNumber;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class WalletRequestDto extends GenericDto {


    private Long walletId;


    @NotBlank
    @NumberFormat
    @CardNumber
    private String walletCode;
    @NotBlank
    private Long balance;
    private Boolean activeStatus;

    private Long userId;

}
