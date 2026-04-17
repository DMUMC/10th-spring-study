package com.UmcSpringStudy.jingjing2.global.exception.errorcodes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE_4040", "해당 가게를 찾을 수 없습니다."),
    STORE_ALREADY_EXISTS(HttpStatus.CONFLICT, "STORE_4090", "이미 등록된 가게입니다."),
    STORE_NOT_OPEN(HttpStatus.BAD_REQUEST, "STORE_4000", "현재 영업 시간이 아닙니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}