package com.umcstudy.jace.domain.user.exception.code;

import com.umcstudy.jace.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements BaseErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER404_1", "사용자를 찾을 수 없습니다."),
    UNSUPPORTED_SOCIAL_PROVIDER(HttpStatus.BAD_REQUEST, "USER400_1", "지원하지 않는 소셜 로그인입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
