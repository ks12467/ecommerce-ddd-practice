package com.hyeonlo.ecommerce.domain.auth.controller;

import com.hyeonlo.ecommerce.domain.auth.dto.request.CreateUserRequest;
import com.hyeonlo.ecommerce.domain.auth.dto.response.CreateUserResponse;
import com.hyeonlo.ecommerce.domain.auth.service.AuthService;
import com.hyeonlo.ecommerce.domain.auth.status.SuccessAuthStatus;
import com.hyeonlo.ecommerce.global.apipayload.BaseResponse;
import com.hyeonlo.ecommerce.global.apipayload.status.SuccessStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    public BaseResponse<CreateUserResponse> createUser(@RequestBody CreateUserRequest createUserRequest) {
        CreateUserResponse response = authService.createUser(createUserRequest);
        return BaseResponse.authSuccess(SuccessAuthStatus.CREATE_USER, response);
    }
}
