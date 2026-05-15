package com.example.umc10th_week04.domain.user.exception.code;

import com.example.umc10th_week04.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements BaseErrorCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND,
            "USER404_1",
            "해당 사용자를 찾을 수 없습니다."),
    EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT,
            "USER409_1",
            "이미 가입된 이메일입니다."),
    INVALID_USER_INFO(HttpStatus.BAD_REQUEST,
            "USER400_1",
            "사용자 정보가 올바르지 않습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
