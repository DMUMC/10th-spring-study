package com.UmcSpringStudy.jingjing2.global.exception;

import com.UmcSpringStudy.jingjing2.global.exception.errorcodes.BaseErrorCode;
import com.UmcSpringStudy.jingjing2.global.exception.errorcodes.CommonErrorCode;
import com.UmcSpringStudy.jingjing2.global.response.CommonResponse;
import com.UmcSpringStudy.jingjing2.global.response.ErrorDetail;
import com.UmcSpringStudy.jingjing2.global.response.FieldErrorDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<CommonResponse<Void>> handleCustomException(CustomException e) {
        // 비즈니스 로직 처리
        BaseErrorCode errorCode = e.getErrorCode();

        ErrorDetail errorDetail = ErrorDetail.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(CommonResponse.error(errorCode.getHttpStatus().value(), errorDetail));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<CommonResponse<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        // 유효성 검사 처리
        BaseErrorCode errorCode = CommonErrorCode.INVALID_PARAMETER;

        List<FieldErrorDetail> fieldErrors = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new FieldErrorDetail(
                        error.getField(),
                        error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                        error.getDefaultMessage()))
                .collect(Collectors.toList());

        ErrorDetail errorDetail = ErrorDetail.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .errors(fieldErrors)
                .build();

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(CommonResponse.error(errorCode.getHttpStatus().value(), errorDetail));
    }
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<CommonResponse<Void>> handleException(Exception ex) {
        //기타
        BaseErrorCode errorCode = CommonErrorCode.INTERNAL_SERVER_ERROR;

        ErrorDetail errorDetail = ErrorDetail.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(CommonResponse.error(errorCode.getHttpStatus().value(), errorDetail));
    }
}