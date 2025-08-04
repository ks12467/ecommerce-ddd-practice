package com.hyeonlo.ecommerce.global.apipayload;

import com.hyeonlo.ecommerce.domain.auth.status.SuccessAuthStatus;
import com.hyeonlo.ecommerce.global.apipayload.status.SuccessStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
public class BaseResponse<T> {

    private final boolean success;
    private final T data;
    private final String code;
    private final String message;
    private final int status;

    public static <T> BaseResponse<T> success(SuccessStatus successStatus, T data) {
        return new BaseResponse<>(
                true,
                data,
                successStatus.getCode(),
                successStatus.getMessage(),
                successStatus.getStatus()
        );
    }

    public static <T> BaseResponse<T> fail(BaseCode baseCode) {
        return new BaseResponse<>(
                false,
                null,
                baseCode.getCode(),
                baseCode.getMessage(),
                baseCode.getStatus()
        );
    }

    public static <T> BaseResponse<T> authSuccess(SuccessAuthStatus successAuthStatus, T data) {
        return new BaseResponse<>(
                true,
                data,
                successAuthStatus.getCode(),
                successAuthStatus.getMessage(),
                successAuthStatus.getStatus()
        );
    }
}
