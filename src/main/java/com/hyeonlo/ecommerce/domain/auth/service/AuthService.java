package com.hyeonlo.ecommerce.domain.auth.service;

import com.hyeonlo.ecommerce.domain.auth.dto.request.CreateUserRequest;
import com.hyeonlo.ecommerce.domain.auth.dto.response.CreateUserResponse;
import com.hyeonlo.ecommerce.domain.auth.status.ErrorAuthStatus;
import com.hyeonlo.ecommerce.domain.client.user.UserClient;
import com.hyeonlo.ecommerce.domain.client.user.dto.CreateUserDto;
import com.hyeonlo.ecommerce.global.exception.BaseException;
import com.hyeonlo.ecommerce.global.security.JwtUtil;
import com.hyeonlo.ecommerce.global.util.PasswordEncoder;
import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserClient userClient;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public CreateUserResponse createUser(CreateUserRequest createUserRequest) {
        if(userClient.existsByLoginId(createUserRequest.getLoginId())) {
            throw new BaseException(ErrorAuthStatus.USER_NOT_FOUNT);
        }
        String encodedPassword = passwordEncoder.encode(createUserRequest.getPassword());
        CreateUserDto userDto = userClient.createUser(createUserRequest.userDto(encodedPassword));

        return CreateUserResponse.of(
            userDto.getLoginId(), userDto.getUserName()
        );
    }
}
