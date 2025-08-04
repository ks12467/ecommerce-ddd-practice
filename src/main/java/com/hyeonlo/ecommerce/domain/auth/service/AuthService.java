package com.hyeonlo.ecommerce.domain.auth.service;

import com.hyeonlo.ecommerce.domain.auth.dto.request.CreateUserRequest;
import com.hyeonlo.ecommerce.domain.auth.dto.request.LoginRequest;
import com.hyeonlo.ecommerce.domain.auth.dto.response.CreateUserResponse;
import com.hyeonlo.ecommerce.domain.auth.dto.response.LoginResponse;
import com.hyeonlo.ecommerce.domain.auth.entity.RefreshToken;
import com.hyeonlo.ecommerce.domain.auth.repository.RefreshTokenRepository;
import com.hyeonlo.ecommerce.domain.auth.status.ErrorAuthStatus;
import com.hyeonlo.ecommerce.domain.client.user.UserClient;
import com.hyeonlo.ecommerce.domain.client.user.dto.CreateUserDto;
import com.hyeonlo.ecommerce.domain.client.user.dto.UserDto;
import com.hyeonlo.ecommerce.domain.user.enums.UserStatus;
import com.hyeonlo.ecommerce.global.exception.BaseException;
import com.hyeonlo.ecommerce.global.security.JwtUtil;
import com.hyeonlo.ecommerce.global.util.PasswordEncoder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserClient userClient;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public CreateUserResponse createUser(CreateUserRequest createUserRequest) {
        if (userClient.existsByLoginId(createUserRequest.getLoginId())) {
            throw new BaseException(ErrorAuthStatus.USER_NOT_FOUNT);
        }
        String encodedPassword = passwordEncoder.encode(createUserRequest.getPassword());
        CreateUserDto userDto = userClient.createUser(createUserRequest.userDto(encodedPassword));

        return CreateUserResponse.of(
                userDto.getLoginId(), userDto.getUserName()
        );
    }

    public LoginResponse login(LoginRequest loginRequest) {
        UserDto user = userClient.findByLoginId(loginRequest.getLoginId());

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BaseException(ErrorAuthStatus.INVALID_PASSWORD);
        }

        if (user.getUserStatus().equals(UserStatus.WITHDRAW)) {
            throw new BaseException(ErrorAuthStatus.WITHDRAW_USER);
        }

        String token = jwtUtil.createToken(
                user.getUserId(),
                user.getLoginId(),
                user.getUserName(),
                user.getUserRole()
        );
        String refreshToken = jwtUtil.createRefreshToken(user.getUserId());
        saveRefreshToken(user.getUserId(),refreshToken);

        return LoginResponse.of(
                token,
                jwtUtil.substringToken(token) + "인증 되었습니다."
        );
    }

    private void saveRefreshToken(Long userId, String refreshToken) {
        RefreshToken token = RefreshToken.builder().userId().token(refreshToken).build();
        refreshTokenRepository.save(token);
    }


}
