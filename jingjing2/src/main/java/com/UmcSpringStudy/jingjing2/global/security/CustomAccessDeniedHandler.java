package com.UmcSpringStudy.jingjing2.global.security;

import com.UmcSpringStudy.jingjing2.global.response.CommonResponse;
import com.UmcSpringStudy.jingjing2.global.response.ErrorDetail;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(@NonNull HttpServletRequest request, HttpServletResponse response, @NonNull AccessDeniedException accessDeniedException) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        // 1. ErrorDetail 객체 생성 (프로젝트 규격 적용)
        ErrorDetail errorDetail = ErrorDetail.builder()
                .code("AUTH_403")
                .message("해당 API에 접근할 권한이 없습니다.")
                .errors(null)
                .build();

        // 2. CommonResponse의 error 메서드 호출
        CommonResponse<Void> errorResponse = CommonResponse.error(
                HttpServletResponse.SC_FORBIDDEN,
                errorDetail
        );

        // 3. JSON으로 직렬화하여 응답
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}