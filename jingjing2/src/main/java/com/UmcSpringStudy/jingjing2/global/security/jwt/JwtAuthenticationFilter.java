package com.UmcSpringStudy.jingjing2.global.security.jwt;

import com.UmcSpringStudy.jingjing2.global.config.SecurityConfig;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    private final AntPathMatcher pathMatcher = new AntPathMatcher();
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String token = resolveToken(request);

        if (token != null && jwtProvider.validateToken(token, request)) {
            // DB 조회 없이 토큰에서 추출
            Claims claims = jwtProvider.getClaims(token);
            String sub = claims.getSubject();

            // 권한은 아직 구현 안함에 따른 빈값 주입
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    sub, null, Collections.emptyList()
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    // 로그인, 회원가입, 스웨거 등은 토큰 검사 생략
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();

        // 1. SecurityConfig에 정의된 PUBLIC_URLS 배열 검사
        for (String publicUrl : SecurityConfig.PUBLIC_URLS) {
            if (pathMatcher.match(publicUrl, path)) {
                return true;
            }
        }
        return false;
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}