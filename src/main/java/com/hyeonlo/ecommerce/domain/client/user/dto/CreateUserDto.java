package com.hyeonlo.ecommerce.domain.client.user.dto;

import com.hyeonlo.ecommerce.domain.user.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {

    private String loginId;
    private String password;
    private String userName;
    private String email;
    private UserRole userRole;
}
