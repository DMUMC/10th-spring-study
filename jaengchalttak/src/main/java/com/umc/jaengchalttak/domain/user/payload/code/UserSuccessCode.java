package com.umc.jaengchalttak.domain.user.payload.code;

import com.umc.jaengchalttak.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserSuccessCode implements BaseSuccessCode {
    // ====== 200 ======
    USER_CHECK_OK(HttpStatus.OK,
            "USER200_1",
            "성공적으로 유저를 조회했습니다."),
    LOGIN_OK(HttpStatus.OK,
            "USER200_2",
            "성공적으로 유저가 로그인 했습니다."),
    LOGOUT_OK(HttpStatus.OK,
            "USER200_3",
            "성공적으로 유저가 로그아웃 했습니다."),
    ALARM_OK(HttpStatus.OK,
            "USER200_4",
            "성공적으로 포인트를 조회했습니다."),
    POINT_CHECK_OK(HttpStatus.OK,
            "USER200_5",
            "성공적으로 포인트를 조회했습니다."),
    CHANGE_NAME_OK(HttpStatus.OK,
            "USER200_6",
            "성공적으로 이름을 수정했습니다."),

    // ====== 201 ======
    USER_CREATED(
            HttpStatus.CREATED,
            "USER201_1",
            "성공적으로 회원 가입이 되었습니다."),

    // ====== 204 ======
    DELETE_ACCOUNT_NO_CONTENT(
            HttpStatus.NO_CONTENT,
            "USER204_1",
            "성공적으로 회원 탈퇴가 되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
