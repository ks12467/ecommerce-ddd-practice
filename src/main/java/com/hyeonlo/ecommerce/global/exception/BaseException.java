package com.hyeonlo.ecommerce.global.exception;

import com.hyeonlo.ecommerce.global.apipayload.BaseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BaseException extends RuntimeException{

    private final BaseCode baseCode;
}
