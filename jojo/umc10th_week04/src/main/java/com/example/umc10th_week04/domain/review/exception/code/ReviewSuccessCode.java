package com.example.umc10th_week04.domain.review.exception.code;

import com.example.umc10th_week04.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,
            "REVIEW200_1",
            "성공적으로 리뷰 요청을 처리했습니다."),
    CREATED(HttpStatus.CREATED,
            "REVIEW201_1",
            "리뷰가 성공적으로 작성되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
