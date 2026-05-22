package com.UmcSpringStudy.jingjing2.global.security;

import com.UmcSpringStudy.jingjing2.global.response.CommonResponse;
import com.UmcSpringStudy.jingjing2.global.response.ErrorDetail;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(@NonNull HttpServletRequest request, HttpServletResponse response, @NonNull AuthenticationException authException) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // 1. ErrorDetail 객체 생성 (프로젝트 규격 적용)
        ErrorDetail errorDetail = ErrorDetail.builder()
                .code("AUTH_401")
                .message("인증이 필요합니다. (토큰 누락 또는 유효하지 않음)")
                .errors(null) // 상세 필드 에러가 없으므로 null 또는 빈 리스트
                .build();

        // 2. CommonResponse의 error 메서드 호출
        CommonResponse<Void> errorResponse = CommonResponse.error(
                HttpServletResponse.SC_UNAUTHORIZED,
                errorDetail
        );

        // 3. JSON으로 직렬화하여 응답
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}