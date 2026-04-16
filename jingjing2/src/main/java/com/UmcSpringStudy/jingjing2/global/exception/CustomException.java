package com.UmcSpringStudy.jingjing2.global.exception;

import com.UmcSpringStudy.jingjing2.global.exception.errorcodes.BaseErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final BaseErrorCode errorCode;

    public CustomException(BaseErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}