package com.hyeonlo.ecommerce.domain.user.controller;

import com.hyeonlo.ecommerce.domain.client.user.dto.CreateUserDto;
import com.hyeonlo.ecommerce.domain.user.dto.request.UpdateUserRequest;
import com.hyeonlo.ecommerce.domain.user.dto.response.UpdateUserResponse;
import com.hyeonlo.ecommerce.domain.user.dto.response.UserProfileResponse;
import com.hyeonlo.ecommerce.domain.user.service.UserService;
import com.hyeonlo.ecommerce.domain.user.status.SuccessUserStatus;
import com.hyeonlo.ecommerce.global.apipayload.BaseResponse;
import com.hyeonlo.ecommerce.global.security.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/profiles")
    public BaseResponse<UserProfileResponse> profile(@AuthenticationPrincipal AuthUser authUser) {
        UserProfileResponse response = userService.profile(authUser);
        return BaseResponse.userSuccess(SuccessUserStatus.ACCEPTED_REQUEST, response);
    }

    @PatchMapping("/v1/profiles")
    public BaseResponse<UpdateUserResponse> updateUser(@AuthenticationPrincipal AuthUser authUser, @RequestBody UpdateUserRequest updateUserRequest) {
        UpdateUserResponse response = userService.updateUser(authUser, updateUserRequest);
        return BaseResponse.userSuccess(SuccessUserStatus.UPDATE_SUCCESS, response);
    }

    @DeleteMapping("/v1")
    public BaseResponse<Void> deleteUser(@AuthenticationPrincipal AuthUser authUser) {
        userService.deleteUser(authUser);
        return BaseResponse.userSuccess(SuccessUserStatus.DELETE_SUCCESS, null);
    }

    //Auth 응답용
    @PostMapping("/v1")
    public BaseResponse<CreateUserDto> createUser(@RequestBody CreateUserDto createUserDto) {
        CreateUserDto response = userService.createUser(createUserDto);
        return BaseResponse.userSuccess(SuccessUserStatus.CREATE_USER, response);
    }
}
