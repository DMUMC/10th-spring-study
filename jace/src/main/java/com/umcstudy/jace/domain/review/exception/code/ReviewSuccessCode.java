package com.umcstudy.jace.domain.review.exception.code;

import com.umcstudy.jace.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {
    OK(HttpStatus.OK, "REVIEW200_1", "리뷰 작성에 성공했습니다."),
    GET_OK(HttpStatus.OK, "REVIEW200_2", "리뷰 목록 조회에 성공했습니다."),
    MY_REVIEW_OK(HttpStatus.OK, "REVIEW200_3", "내 리뷰 목록 조회에 성공했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
