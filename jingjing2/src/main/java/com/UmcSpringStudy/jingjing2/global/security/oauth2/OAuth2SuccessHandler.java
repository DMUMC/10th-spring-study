package com.UmcSpringStudy.jingjing2.global.security.oauth2;

import com.UmcSpringStudy.jingjing2.domain.user.dto.user.response.LoginResponse;
import com.UmcSpringStudy.jingjing2.global.response.CommonResponse;
import com.UmcSpringStudy.jingjing2.global.security.jwt.JwtProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(@NonNull HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        // 1. PK 추출 (실제 DB ID)
        String userId = Objects.requireNonNull(Objects.requireNonNull(oAuth2User).getAttribute("dbUserId")).toString();

        // 2. Access Token 생성
        String accessToken = jwtProvider.createAccessToken("KAKAO", userId);

        // 3. HTTP 응답 헤더 설정 (JSON 타입 및 상태 코드 200)
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);

        // 4. 로컬 로그인과 동일한 형태의 응답 데이터 구성 (CommonResponse로 감싸기)
        LoginResponse loginResponse = new LoginResponse(accessToken);
        CommonResponse<LoginResponse> responseBody = CommonResponse.success("소셜 로그인 성공", loginResponse);

        // 5. 객체를 JSON 문자열로 변환하여 딱 한 번만 HTTP Response Body에 출력
        objectMapper.writeValue(response.getWriter(), responseBody);
    }
}