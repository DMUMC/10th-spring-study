package com.umc.jaengchalttak.global.security.handler;

import com.umc.jaengchalttak.global.apiPayload.code.GeneralErrorCode;
import com.umc.jaengchalttak.global.security.util.ErrorResponseWriterUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

@Slf4j
public class OAuthAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull AuthenticationException exception
    ) throws IOException, ServletException {

        log.error(
                "[OAuth2 로그인 실패] uri={}, message={}",
                request.getRequestURI(),
                exception.getMessage(),
                exception
        );

        // 공통 에러 응답 반환
        ErrorResponseWriterUtil.write(
                response,
                GeneralErrorCode.UNAUTHORIZED
        );
    }
}