package com.umc.jaengchalttak.global.security.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umc.jaengchalttak.global.apiPayload.ApiResponse;
import com.umc.jaengchalttak.global.apiPayload.code.BaseErrorCode;
import com.umc.jaengchalttak.global.apiPayload.code.GeneralErrorCode;
import com.umc.jaengchalttak.global.apiPayload.exception.ProjectException;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ErrorResponseWriterUtil {

    private ErrorResponseWriterUtil() {
        throw new ProjectException(GeneralErrorCode.UTILITY_CLASS_INSTANTIATION);
    }

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static void write(HttpServletResponse response, BaseErrorCode code) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(code.getStatus().value());

        ApiResponse<Void> errorResponse =
                ApiResponse.onFailure(code, null);

        OBJECT_MAPPER.writeValue(
                response.getOutputStream(),
                errorResponse
        );
    }

}
