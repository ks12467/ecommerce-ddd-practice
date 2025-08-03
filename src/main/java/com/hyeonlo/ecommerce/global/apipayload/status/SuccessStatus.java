package com.hyeonlo.ecommerce.global.apipayload.status;

import com.hyeonlo.ecommerce.global.apipayload.BaseCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum SuccessStatus implements BaseCode {

    OK(HttpStatus.OK.value(), "S001", "요청이 성공적으로 처리되었습니다.");

    private int status;
    private String code;
    private String message;

    SuccessStatus(int status, String code,  String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
