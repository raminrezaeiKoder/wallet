package com.digitalwallet.dto.response;

import com.digitalwallet.generic.GenericDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponseDto extends GenericDto {

    private String name ;
    private String lastName ;
    private String email ;
    private String phoneNumber ;


}
