package com.hyeonlo.ecommerce.domain.client.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long userId;
    private String loginId;
    private String password;
    private String userName;
    private UserRole userRole;
    private UserStatus userStatus;
}
