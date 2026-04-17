package com.umcstudy.jace.global.apiPayload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.umcstudy.jace.global.apiPayload.code.BaseErrorCode;
import com.umcstudy.jace.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"status", "code", "message", "data"})
public class ApiResponse<T> {

    @JsonProperty("status")
    private final int status;

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("data")
    private final T data;

    // 성공한 경우
    public static <T> ApiResponse<T> onSuccess(BaseSuccessCode successCode, T result) {
        return new ApiResponse<>(
                successCode.getStatus().value(),                 // 200
                successCode.getStatus().getReasonPhrase(),       // Not Found
                successCode.getMessage(),                        // 찾을 수 없습니다
                result
        );
    }

    // 실패한 경우
    public static <T> ApiResponse<T> onFailure(BaseErrorCode errorCode, T result) {
        return new ApiResponse<>(
                errorCode.getStatus().value(),                 // 404
                errorCode.getStatus().getReasonPhrase(),       // Not Found
                errorCode.getMessage(),                        // 찾을 수 없습니다
                result
        );
    }
}
