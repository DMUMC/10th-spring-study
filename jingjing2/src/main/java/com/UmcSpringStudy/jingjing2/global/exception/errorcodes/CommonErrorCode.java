package com.UmcSpringStudy.jingjing2.global.exception.errorcodes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CommonErrorCode implements BaseErrorCode {

    // --- 400 BAD REQUEST ---
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "COMMON_4000", "잘못된 파라미터입니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON_4001", "잘못된 요청입니다."),
    INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST, "COMMON_4002", "타입이 일치하지 않습니다."),
    MISSING_INPUT_VALUE(HttpStatus.BAD_REQUEST, "COMMON_4003", "필수 입력값이 누락되었습니다."),

    // --- 401 UNAUTHORIZED ---
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON_4010", "인증이 필요합니다."),
    LOGIN_FAILED(HttpStatus.UNAUTHORIZED, "COMMON_4011", "로그인에 실패하였습니다."),

    // --- 403 FORBIDDEN ---
    FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON_4030", "접근 권한이 없습니다."),

    // --- 404 NOT FOUND ---
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON_4040", "리소스를 찾을 수 없습니다."),

    // --- 405 METHOD NOT ALLOWED ---
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "COMMON_4050", "허용되지 않은 HTTP 메소드입니다."),

    // --- 409 CONFLICT ---
    RESOURCE_CONFLICT(HttpStatus.CONFLICT, "COMMON_4090", "데이터 충돌이 발생했습니다."),

    // --- 500 INTERNAL SERVER ERROR ---
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON_5000", "서버 내부 에러입니다."),
    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON_5001", "데이터베이스 연결 에러입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}