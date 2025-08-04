package com.hyeonlo.ecommerce.domain.client.user;

import com.hyeonlo.ecommerce.domain.client.user.dto.CreateUserDto;
import com.hyeonlo.ecommerce.domain.client.user.dto.UserDto;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name = "user-service", url = "http://localhost:8080")
public interface UserClient {

    boolean existsByLoginId(String loginId);

    CreateUserDto createUser(CreateUserDto createUserDto);

    UserDto findByLoginId(String loginId);
}
