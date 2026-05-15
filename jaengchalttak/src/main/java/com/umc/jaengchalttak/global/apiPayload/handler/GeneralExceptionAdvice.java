package com.umc.jaengchalttak.global.apiPayload.handler;

import com.umc.jaengchalttak.global.apiPayload.ApiResponse;
import com.umc.jaengchalttak.global.apiPayload.code.BaseErrorCode;
import com.umc.jaengchalttak.global.apiPayload.code.GeneralErrorCode;
import com.umc.jaengchalttak.global.apiPayload.exception.ProjectException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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

        return ResponseEntity.status(errorCode.getStatus())
                .body(ApiResponse.onFailure(
                        errorCode,
                        null
                ));
    }

    // 그 외의 정의되지 않은 모든 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(
            Exception ex
    ) {

        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(
                                code,
                                ex.getMessage()
                        )
                );
    }

    // 유효성 검증 관련 예외
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<String>> handleConstraintViolationException(ConstraintViolationException e) {
        String errorMessage = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .findFirst()
                .orElse("유효성 검증에 실패했습니다.");

        BaseErrorCode errorCode = GeneralErrorCode.INVALID_INPUT_VALUE;

        return ResponseEntity.status(errorCode.getStatus())
                .body(ApiResponse.onFailure(
                        errorCode,
                        errorMessage
                ));
    }
}
