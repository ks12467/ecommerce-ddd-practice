package com.hyeonlo.ecommerce.domain.user.status;

import com.hyeonlo.ecommerce.global.apipayload.BaseCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorUserStatus implements BaseCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "EU001", "유저를 찾을 수 없습니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST.value(), "EU002", "비밀번호가 일치하지 않습니다.");

    private int status;
    private String code;
    private String message;

    ErrorUserStatus(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
