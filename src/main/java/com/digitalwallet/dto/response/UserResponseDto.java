package com.digitalwallet.dto.response;

import com.digitalwallet.generic.GenericDto;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponseDto extends GenericDto {

    private Long id;

    private String userName;

    private String email;

    private List<String> rolesList;

}
