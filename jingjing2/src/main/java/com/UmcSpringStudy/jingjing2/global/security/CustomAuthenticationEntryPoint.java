package com.UmcSpringStudy.jingjing2.global.security;

import com.UmcSpringStudy.jingjing2.global.response.CommonResponse;
import com.UmcSpringStudy.jingjing2.global.response.ErrorDetail;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.ObjectProvider; // ✨ ObjectProvider 임포트
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectProvider<ObjectMapper> objectMapperProvider;

    @Override
    public void commence(@NonNull HttpServletRequest request, HttpServletResponse response, @NonNull AuthenticationException authException) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ErrorDetail errorDetail = ErrorDetail.builder()
                .code("AUTH_401")
                .message("인증이 필요합니다. (토큰 누락 또는 유효하지 않음)")
                .errors(null)
                .build();

        CommonResponse<Void> errorResponse = CommonResponse.error(
                HttpServletResponse.SC_UNAUTHORIZED,
                errorDetail
        );

        ObjectMapper objectMapper = objectMapperProvider.getObject();
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}