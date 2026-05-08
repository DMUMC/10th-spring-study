package com.umcstudy.jace.global.apiPayload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.umcstudy.jace.global.apiPayload.code.BaseErrorCode;
import com.umcstudy.jace.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"code", "message", "data"})
public class ApiResponse<T> {

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("data")
    private final T data;

    public static <T> ApiResponse<T> onSuccess(BaseSuccessCode successCode, T result) {
        return new ApiResponse<>(
                successCode.getCode(),
                successCode.getMessage(),
                result
        );
    }

    public static <T> ApiResponse<T> onFailure(BaseErrorCode errorCode, T result) {
        return new ApiResponse<>(
                errorCode.getCode(),
                errorCode.getMessage(),
                result
        );
    }
}
