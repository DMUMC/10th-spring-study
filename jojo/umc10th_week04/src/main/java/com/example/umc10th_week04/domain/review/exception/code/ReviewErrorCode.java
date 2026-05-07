package com.example.umc10th_week04.domain.review.exception.code;

import com.example.umc10th_week04.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND,
            "REVIEW404_1",
            "해당 리뷰를 찾을 수 없습니다."),
    INVALID_REVIEW_SCORE(HttpStatus.BAD_REQUEST,
            "REVIEW400_1",
            "리뷰 점수가 올바르지 않습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
