package com.UmcSpringStudy.jingjing2.global.exception.errorcodes;

import org.springframework.http.HttpStatus;

public interface BaseErrorCode {
    HttpStatus getHttpStatus();
    String getCode();
    String getMessage();
}