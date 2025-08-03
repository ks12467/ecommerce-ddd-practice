package com.hyeonlo.ecommerce.global.exception;

import com.hyeonlo.ecommerce.global.apipayload.BaseCode;
import com.hyeonlo.ecommerce.global.apipayload.BaseResponse;
import com.hyeonlo.ecommerce.global.apipayload.status.ErrorStatus;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseResponse<Object>> customExceptionHandler(BaseException e) {
        BaseCode errorCode = e.getBaseCode();
        return handlerExceptionInternal(errorCode);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BaseResponse<Object>> handlerIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity.status(ErrorStatus.INVALID_REQUEST.getStatus()).body(BaseResponse.fail(ErrorStatus.INVALID_REQUEST));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Object>> handlerUnException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.fail(ErrorStatus.INTERNAL_SERVER_ERROR));
    }

    private ResponseEntity<BaseResponse<Object>> handlerExceptionInternal(BaseCode errorStatus) {
        return ResponseEntity.status(errorStatus.getStatus()).body(BaseResponse.fail(errorStatus));
    }
}

