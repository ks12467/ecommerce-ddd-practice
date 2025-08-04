package com.hyeonlo.ecommerce.domain.auth.controller;

import com.hyeonlo.ecommerce.domain.auth.dto.request.CreateUserRequest;
import com.hyeonlo.ecommerce.domain.auth.dto.request.LoginRequest;
import com.hyeonlo.ecommerce.domain.auth.dto.response.CreateUserResponse;
import com.hyeonlo.ecommerce.domain.auth.dto.response.LoginResponse;
import com.hyeonlo.ecommerce.domain.auth.service.AuthService;
import com.hyeonlo.ecommerce.domain.auth.status.SuccessAuthStatus;
import com.hyeonlo.ecommerce.global.apipayload.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/v1/register")
    public BaseResponse<CreateUserResponse> createUser(@RequestBody CreateUserRequest createUserRequest) {
        CreateUserResponse response = authService.createUser(createUserRequest);
        return BaseResponse.authSuccess(SuccessAuthStatus.CREATE_USER, response);
    }

    @PostMapping("/v1/login")
    public BaseResponse<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = authService.login(loginRequest);
        return BaseResponse.authSuccess(SuccessAuthStatus.LOGIN_SUCCESS, response);
    }
}
