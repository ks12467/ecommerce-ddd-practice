package com.hyeonlo.ecommerce.domain.client.user;

import com.hyeonlo.ecommerce.domain.client.user.dto.CreateUserDto;
import com.hyeonlo.ecommerce.domain.client.user.dto.UserDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "user-service", url = "http://localhost:8080")
public interface UserClient {

    @PostMapping("/api/user/v1")
    CreateUserDto createUser(CreateUserDto createUserDto);

    boolean existsByLoginId(String loginId);

    UserDto findByLoginId(String loginId);
}
