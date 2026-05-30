package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER404_1",
            "해당 사용자를 찾을 수 없습니다."),
    NOT_SUPPORT_SOCIAL_PROVIDER(HttpStatus.NOT_FOUND,
            "MEMBER404_2",
            "해당 소셜을 찾을 수 없습니다."),
    NOT_MACHE_LOGIN(HttpStatus.UNAUTHORIZED,
            "COMMON401_1",
            "이메일 또는 비밀번호가 일치하지 않습니다.");



    private final HttpStatus status;
    private final String code;
    private final String message;
}