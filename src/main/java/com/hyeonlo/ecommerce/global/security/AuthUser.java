package com.hyeonlo.ecommerce.global.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Getter
public class AuthUser {

    private final Long userId;
    private final String loginId;
    private final String userName;
    private final Collection<? extends GrantedAuthority> authorities;

    public AuthUser(Long userId, String loginId, String userName, UserRole userRole) {
        this.userId = userId;
        this.loginId = loginId;
        this.userName = userName;
        this.authorities = List.of(
                new SimpleGrantedAuthority(userRole.name())
        );
    }
}
