package com.example.umc10th_week04.global.security.filter;

import com.example.umc10th_week04.domain.user.converter.UserConverter;
import com.example.umc10th_week04.domain.user.entity.User;
import com.example.umc10th_week04.domain.user.enums.Role;
import com.example.umc10th_week04.domain.user.enums.SocialType;
import com.example.umc10th_week04.global.apiPayload.ApiResponse;
import com.example.umc10th_week04.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th_week04.global.apiPayload.code.GeneralErrorCode;
import com.example.umc10th_week04.global.security.entity.AuthUser;
import com.example.umc10th_week04.global.security.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) throws ServletException, IOException {

        try {

            // 토큰 가져오기
            String token = request.getHeader("Authorization");

            // 토큰이 없거나 Bearer가 아니면 넘기기
            if(token == null || !token.startsWith("Bearer ")){
                filterChain.doFilter(request, response);
                return;
            }

            // Bearer이면 추출
            token = token.substring(7);

            // AccessToken 검증하기: 실패 시 즉시 예외 발생
            Claims claims = jwtUtil.validateAndGetClaims(token);

            String roleClaim = claims.get("role", String.class);
            Role role = Role.valueOf(roleClaim.replace("ROLE_", ""));

            User domainUser = User.builder()
                    .id(jwtUtil.getUserId(claims))
                    .name(claims.get("name", String.class))
                    .email(claims.get("email", String.class))
                    .socialType(jwtUtil.getSocialType(claims))
                    .socialUid(jwtUtil.getSocialUid(claims))
                    .role(role)
                    .build();

            // JWT 토큰 클레임으로 인증 객체 생성
            AuthUser user = new AuthUser(domainUser);

            Authentication auth = new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    user.getAuthorities()
            );

            // 인증 완료 후 SecurityContextHolder에 넣기
            SecurityContextHolder.getContext().setAuthentication(auth);

            filterChain.doFilter(request, response);
        } catch (Exception e){
            ObjectMapper mapper = new ObjectMapper();
            BaseErrorCode code = GeneralErrorCode.UNAUTHORIZED;

            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(code.getStatus().value());

            ApiResponse<Void> errorResponse = ApiResponse.onFailure(code, null);

            mapper.writeValue(response.getOutputStream(), errorResponse);
        }
    }

}
