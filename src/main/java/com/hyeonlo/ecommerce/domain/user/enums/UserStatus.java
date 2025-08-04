package com.hyeonlo.ecommerce.domain.user.enums;

import lombok.Getter;

@Getter
public enum UserStatus {

    ACTIVE("활성화"),
    WITHDRAW("탈퇴한 계정");

    private String message;

    UserStatus(String message) {
        this.message = message;
    }
}
