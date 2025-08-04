package com.hyeonlo.ecommerce.domain.user.status;

import com.hyeonlo.ecommerce.global.apipayload.BaseCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum SuccessUserStatus implements BaseCode {

    ACCEPTED_REQUEST (HttpStatus.ACCEPTED.value(), "SU001","프로필 요청이 성공적으로 처리되었습니다."),
    UPDATE_SUCCESS(HttpStatus.ACCEPTED.value(), "SU002" , "회원 정보가 수정되었습니다.");


    private int status;
    private String code;
    private String message;

    SuccessUserStatus(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
