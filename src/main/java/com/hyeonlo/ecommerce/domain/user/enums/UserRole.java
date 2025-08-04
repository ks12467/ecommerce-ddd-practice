package com.hyeonlo.ecommerce.domain.user.enums;

import lombok.Getter;

@Getter
public enum UserRole {

    USER ("유저"),
    ADMIN("관리자");

    private String message;

    UserRole(String message) {
        this.message = message;
    }
}
