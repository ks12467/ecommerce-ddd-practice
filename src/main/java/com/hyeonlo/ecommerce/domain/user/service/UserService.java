package com.hyeonlo.ecommerce.domain.user.service;

import com.hyeonlo.ecommerce.domain.client.user.dto.CreateUserDto;
import com.hyeonlo.ecommerce.domain.client.user.dto.UserDto;
import com.hyeonlo.ecommerce.domain.user.dto.request.UpdateUserRequest;
import com.hyeonlo.ecommerce.domain.user.dto.response.UpdateUserResponse;
import com.hyeonlo.ecommerce.domain.user.dto.response.UserProfileResponse;
import com.hyeonlo.ecommerce.domain.user.entity.Users;
import com.hyeonlo.ecommerce.domain.user.enums.UserStatus;
import com.hyeonlo.ecommerce.domain.user.repository.UserRepository;
import com.hyeonlo.ecommerce.domain.user.status.ErrorUserStatus;
import com.hyeonlo.ecommerce.global.exception.BaseException;
import com.hyeonlo.ecommerce.global.security.AuthUser;
import com.hyeonlo.ecommerce.global.util.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserProfileResponse profile(AuthUser authUser) {
        Users user = userRepository.findById(authUser.getUserId())
                .orElseThrow(() -> new BaseException(ErrorUserStatus.USER_NOT_FOUND));

        return UserProfileResponse.of(
                user.getUserId(),
                user.getLoginId(),
                user.getUserName(),
                user.getEmail(),
                user.getUserRole(),
                user.getUserStatus()
        );
    }

    public UpdateUserResponse updateUser(AuthUser authUser, UpdateUserRequest updateUserRequest) {
        Users user = userRepository.findById(authUser.getUserId())
                .orElseThrow(() -> new BaseException(ErrorUserStatus.USER_NOT_FOUND));

        if(!passwordEncoder.matches(updateUserRequest.getCurrentedPassword(), user.getPassword())) {
            throw new BaseException(ErrorUserStatus.INVALID_PASSWORD);
        }
        if(updateUserRequest.getLoginId() != null && !updateUserRequest.getLoginId().isBlank()) {
            user.updateLoginId(updateUserRequest.getLoginId());
        }

        if(updateUserRequest.getPassword() != null && !updateUserRequest.getPassword().isBlank()) {
            String newEncodedPassword = passwordEncoder.encode(updateUserRequest.getPassword());
            user.updatePassword(newEncodedPassword);
        }

        if(updateUserRequest.getUserName() != null && !updateUserRequest.getUserName().isBlank()) {
            user.updateUserName(updateUserRequest.getUserName());
        }

        if(updateUserRequest.getEmail() != null && !updateUserRequest.getEmail().isBlank()) {
            user.updateEmail(updateUserRequest.getEmail());
        }

        userRepository.save(user);

        return UpdateUserResponse.of(
                "회원 정보 수정 완료"
        );
    }

    public void deleteUser(AuthUser authUser) {
        Users user = userRepository.findById(authUser.getUserId())
                .orElseThrow(() -> new BaseException(ErrorUserStatus.USER_NOT_FOUND));
        user.chageStatus();
    }

    public CreateUserDto createUser(CreateUserDto createUserDto) {
        Users user = Users.from(
                createUserDto.getLoginId(),
                createUserDto.getPassword(),
                createUserDto.getUserName(),
                createUserDto.getEmail(),
                createUserDto.getUserRole(),
                UserStatus.ACTIVE
        );
        userRepository.save(user);
        return new CreateUserDto(
                user.getLoginId(),
                user.getPassword(),
                user.getUserName(),
                user.getEmail(),
                user.getUserRole()
        );
    }
}
