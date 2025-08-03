package com.hyeonlo.ecommerce.global.apipayload.status;

import com.hyeonlo.ecommerce.global.apipayload.BaseCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorStatus implements BaseCode {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "S999" , "예상치 못한 오류가 발생하였습니다."),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST.value(), "S998", "잘못된 요청입니다."),
    JWT_TOKEN_MISSING(HttpStatus.NOT_FOUND.value(), "S998", "JWT 토큰이 필요합니다."),
    SC_UNAUTHORIZED(HttpStatus.UNAUTHORIZED.value(), "S997", "유효하지 않은 토큰입니다."),
    SC_BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "S996" , "지원하지 않는 토큰입니다.");

    private int status;
    private String code;
    private String message;

    ErrorStatus(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
