package com.UmcSpringStudy.jingjing2.global.exception.errorcodes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    INVALID_FILE_FORMAT(HttpStatus.BAD_REQUEST, "REVIEW_4000", "지원하지 않는 파일 형식입니다."),
    FILE_SIZE_EXCEEDED(HttpStatus.BAD_REQUEST, "REVIEW_4001", "파일 크기가 제한을 초과했습니다."),

    NOT_REVIEW_WRITER(HttpStatus.FORBIDDEN, "REVIEW_4030", "본인이 작성한 리뷰만 수정/삭제할 수 있습니다."),

    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW_4040", "해당 리뷰를 찾을 수 없습니다."),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW_4041", "해당 댓글을 찾을 수 없습니다."),

    FILE_UPLOAD_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "REVIEW_5000", "파일 업로드 중 오류가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}