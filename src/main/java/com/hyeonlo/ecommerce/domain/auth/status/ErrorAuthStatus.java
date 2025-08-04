package com.hyeonlo.ecommerce.domain.auth.status;

import com.hyeonlo.ecommerce.global.apipayload.BaseCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorAuthStatus implements BaseCode {

    USER_NOT_FOUNT(HttpStatus.NOT_FOUND.value(), "AE001" , "유저를 찾을 수 없습니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST.value(), "AE002", "비밀번호가 일치하지 않습니다." ),
    WITHDRAW_USER(HttpStatus.NO_CONTENT.value(), "AE003" , "해당 유저는 탈퇴되었습니다." );

    private int status;
    private String code;
    private String message;

    ErrorAuthStatus(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
