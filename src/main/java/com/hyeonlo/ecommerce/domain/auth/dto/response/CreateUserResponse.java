package com.hyeonlo.ecommerce.domain.auth.dto.response;

import com.hyeonlo.ecommerce.domain.auth.dto.request.CreateUserRequest;
import lombok.Getter;

@Getter
public class CreateUserResponse {

    private final String loginId;
    private final String userName;

    private CreateUserResponse(String loginId, String userName) {
        this.loginId = loginId;
        this.userName = userName;
    }

    public static CreateUserResponse of(String loginId, String userName) {
        return new CreateUserResponse(
                loginId,
                userName
        );
    }
}
