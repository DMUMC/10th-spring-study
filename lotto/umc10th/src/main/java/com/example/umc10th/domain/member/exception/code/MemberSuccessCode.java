package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {
    OK(HttpStatus.OK,
            "MEMBER200_1",
            "조회 요청을 성공했습니다."),
    SIGNUP(HttpStatus.OK,
            "MEMBER200_2",
                    "회원가입 요청을 성공했습니다."),
    LOGIN(HttpStatus.OK,
            "MEMBER200_3",
                    "로그인 요청을 성공했습니다.");;

    private final HttpStatus status;
    private final String code;
    private final String message;
}