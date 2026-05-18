package com.umc.jaengchalttak.global.apiPayload.handler;

import com.umc.jaengchalttak.global.apiPayload.ApiResponse;
import com.umc.jaengchalttak.global.apiPayload.code.BaseErrorCode;
import com.umc.jaengchalttak.global.apiPayload.code.GeneralErrorCode;
import com.umc.jaengchalttak.global.apiPayload.exception.ProjectException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GeneralExceptionAdvice {

    // 프로젝트에서 발생한 예외 처리
    @ExceptionHandler(ProjectException.class)
    public ResponseEntity<ApiResponse<Void>> handleProjectException(
            ProjectException e
    ) {
        BaseErrorCode errorCode = e.getErrorCode();

        log.error("[ProjectException] Code: {}, Message: {}",
                errorCode.getCode(), errorCode.getMessage(), e);

        return errorResponse(errorCode, null);
    }

    // 그 외의 정의되지 않은 모든 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(
            Exception ex
    ) {
        log.error("[Exception]", ex);

        return errorResponse(
                GeneralErrorCode.INTERNAL_SERVER_ERROR,
                ex.getMessage()
        );
    }

    // 요청 본문 검증 실패
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e
    ) {

        String errorMessage = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("요청 본문 값이 올바르지 않습니다.");

        return errorResponse(
                GeneralErrorCode.INVALID_REQUEST_BODY,
                errorMessage
        );
    }

    // 파라미터 검증 실패
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<String>> handleConstraintViolationException(
            ConstraintViolationException e
    ) {

        String errorMessage = e.getConstraintViolations()
                .stream()
                .findFirst()
                .map(ConstraintViolation::getMessage)
                .orElse("요청 파라미터 값이 올바르지 않습니다.");

        return errorResponse(
                GeneralErrorCode.INVALID_REQUEST_PARAMETER,
                errorMessage
        );
    }

    private <T> ResponseEntity<ApiResponse<T>> errorResponse(BaseErrorCode errorCode, T data) {
        return ResponseEntity.status(errorCode.getStatus())
                .body(ApiResponse.onFailure(
                        errorCode,
                        data
                ));
    }

}