package com.umcstudy.jace.domain.user.exception.code;

import com.umcstudy.jace.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserSuccessCode implements BaseSuccessCode {
    SignupOK(HttpStatus.OK, "MEMBER200_1", "회원가입에 성공했습니다."),
    TermsListOK(HttpStatus.OK, "MEMBER200_1", "회원가입에 성공했습니다."),
    FoodsListOK(HttpStatus.OK, "MEMBER200_1", "회원가입에 성공했습니다."),;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
