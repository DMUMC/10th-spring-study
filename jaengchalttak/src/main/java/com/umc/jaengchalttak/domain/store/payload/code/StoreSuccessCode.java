package com.umc.jaengchalttak.domain.store.payload.code;

import com.umc.jaengchalttak.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {

    // ====== 200 ======
    STORE_LIST_OK(HttpStatus.OK,
            "STORE200_1",
            "성공적으로 내 지역의 가게들을 조회했습니다."),
    STORE_INFO_OK(HttpStatus.OK,
            "STORE200_2",
            "성공적으로 가게 상세 정보를 조회했습니다."),
    REVIEW_LIST_OK(HttpStatus.OK,
            "STORE200_3",
            "성공적으로 가게의 리뷰 목록을 조회했습니다."),

    // ====== 201 ======
    REVIEW_CREATED(HttpStatus.CREATED,
            "STORE201_1",
            "성공적으로 리뷰를 작성했습니다."),
    COMMENT_CREATED(HttpStatus.CREATED,
            "STORE201_2",
            "성공적으로 댓글을 작성했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

}
