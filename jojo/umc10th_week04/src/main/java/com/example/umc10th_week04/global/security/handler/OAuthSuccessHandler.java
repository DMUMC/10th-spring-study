package com.example.umc10th_week04.global.security.handler;

import com.example.umc10th_week04.domain.user.converter.UserConverter;
import com.example.umc10th_week04.domain.user.dto.UserResDTO;
import com.example.umc10th_week04.domain.user.exception.code.UserSuccessCode;
import com.example.umc10th_week04.global.apiPayload.ApiResponse;
import com.example.umc10th_week04.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th_week04.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th_week04.global.security.entity.AuthUser;
import com.example.umc10th_week04.global.security.entity.OAuthUser;
import com.example.umc10th_week04.global.security.util.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

@RequiredArgsConstructor
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {
        // 사전 작업 : Response 매핑할 ObjectMapper 선언
        ObjectMapper objectMapper = new ObjectMapper();
        BaseSuccessCode code = UserSuccessCode.OK;

        // Content-Type, Status 설정
        response.setContentType("application/json; charset=UTF-8");
        response.setStatus(code.getStatus().value());

        // 인증 객체 컨테이너에서 OAuth 인증 객체 가져오기
        OAuthUser user = (OAuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 토큰 제작을 위해 OAuth 인증 객체에서 User 추출 -> AuthUser 제작
        String accessToken = jwtUtil.createAccessToken(
                user.getUser().getId(),
                user.getUser().getName(),
                user.getUser().getEmail(),
                user.getUser().getSocialType(),
                user.getUser().getSocialUid(),
                user.getAuthorities()
        );

        // 응답 통일 객체 래핑
        ApiResponse<UserResDTO.Login> responseBody = ApiResponse.onSuccess(
                code,
                UserConverter.toLogin(accessToken)
        );

        // 응답 출력
        objectMapper.writeValue(response.getOutputStream(), responseBody);
    }

}
