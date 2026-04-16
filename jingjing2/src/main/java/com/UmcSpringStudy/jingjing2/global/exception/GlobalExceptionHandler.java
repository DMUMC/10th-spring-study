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
        // 인터페이스(BaseErrorCode)를 통해 값을 가져오므로, 어떤 Enum이 들어와도 동일하게 처리됨
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
        // 공통 에러 코드를 사용할 때도 CommonErrorCode에서 가져옴
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
}