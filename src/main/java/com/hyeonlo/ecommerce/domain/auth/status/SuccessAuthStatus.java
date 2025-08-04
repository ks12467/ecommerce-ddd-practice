package com.hyeonlo.ecommerce.domain.auth.status;

import com.hyeonlo.ecommerce.global.apipayload.BaseCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum SuccessAuthStatus implements BaseCode {

    CREATE_USER(HttpStatus.CREATED.value(), "AS001", "유저가 성공적으로 생성되었습니다."),
    LOGIN_SUCCESS(HttpStatus.ACCEPTED.value(), "AS002", "로그인이 성공적으로 완료되었습니다.");

    private int status;
    private String code;
    private String message;

    SuccessAuthStatus(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
