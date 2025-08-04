package com.hyeonlo.ecommerce.domain.auth.dto.response;

import lombok.Getter;

@Getter
public class LoginResponse {

    private final String token;
    private final String message;

    private LoginResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }

    public static LoginResponse of(String token, String message) {
        return new LoginResponse(
                token,
                message
        );
    }
}
