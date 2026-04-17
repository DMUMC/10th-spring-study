package com.umc.jaengchalttak.domain.inquiry.payload.code;

import com.umc.jaengchalttak.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum InquirySuccessCode implements BaseSuccessCode {

    // ====== 200 ======
    LIST_OK(HttpStatus.OK,
            "INQUIRY200_1",
            "성공적으로 1대1 문의 목록을 조회했습니다."),
    INFO_OK(HttpStatus.OK,
            "INQUIRY200_2",
            "성공적으로 1대1 문의 상세 정보를 조회했습니다."),

    // ====== 201 ======
    SUBMIT_INQUIRY_CREATED(HttpStatus.CREATED,
            "INQUIRY201_1",
            "성공적으로 1대1 문의 제출을 완료했습니다."),

    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

}
