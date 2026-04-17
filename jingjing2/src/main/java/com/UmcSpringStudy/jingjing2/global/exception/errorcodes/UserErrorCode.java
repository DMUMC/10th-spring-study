package com.UmcSpringStudy.jingjing2.global.exception.errorcodes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseErrorCode {

    // --- 400 BAD REQUEST ---
    INVALID_USER_STATUS(HttpStatus.BAD_REQUEST, "USER_4000", "유효하지 않은 유저 상태입니다."),
    POINT_LACK(HttpStatus.BAD_REQUEST, "USER_4001", "포인트가 부족합니다."),

    // --- 401 UNAUTHORIZED ---
    USER_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "USER_4010", "로그인이 필요한 서비스입니다."),

    // --- 403 FORBIDDEN ---
    USER_FORBIDDEN(HttpStatus.FORBIDDEN, "USER_4030", "해당 권한이 없습니다."),

    // --- 404 NOT FOUND ---
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_4040", "존재하지 않는 사용자입니다."),
    INQUIRY_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_4041", "문의사항을 찾을 수 없습니다."),
    INTEREST_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_4042", "존재하지 않는 관심사 카테고리입니다."),

    // --- 409 CONFLICT ---
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "USER_4090", "이미 가입된 이메일입니다."),
    DUPLICATE_PHONE(HttpStatus.CONFLICT, "USER_4091", "이미 등록된 전화번호입니다."),
    ALREADY_DELETED_USER(HttpStatus.CONFLICT, "USER_4092", "이미 탈퇴한 사용자입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}