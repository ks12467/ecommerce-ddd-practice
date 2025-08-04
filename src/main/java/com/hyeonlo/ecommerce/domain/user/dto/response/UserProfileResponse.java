package com.hyeonlo.ecommerce.domain.user.dto.response;

import com.hyeonlo.ecommerce.domain.user.enums.UserRole;
import com.hyeonlo.ecommerce.domain.user.enums.UserStatus;
import com.hyeonlo.ecommerce.domain.user.service.UserService;
import lombok.Getter;

@Getter
public class UserProfileResponse {

    private final Long userId;
    private final String loginId;
    private final String userName;
    private final String email;
    private final UserRole userRole;
    private final UserStatus userStatus;


    private UserProfileResponse(
            Long userId,
            String loginId,
            String userName,
            String email,
            UserRole userRole,
            UserStatus userStatus) {
        this.userId = userId;
        this.loginId = loginId;
        this.userName = userName;
        this.email = email;
        this.userRole = userRole;
        this.userStatus = userStatus;
    }

    public static UserProfileResponse of(
            Long userId,
            String loginId,
            String userName,
            String email,
            UserRole userRole,
            UserStatus userStatus) {
        return new UserProfileResponse(
                userId,
                loginId,
                userName,
                email,
                userRole,
                userStatus
        );
    }
}
