package com.UmcSpringStudy.jingjing2.global.config;

import com.UmcSpringStudy.jingjing2.global.security.CustomAccessDeniedHandler;
import com.UmcSpringStudy.jingjing2.global.security.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationEntryPoint authenticationEntryPoint;
    private final CustomAccessDeniedHandler accessDeniedHandler;

    // BCrypt를 사용한 패스워드 인코더 빈 등록
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // REST API이므로 CSRF, Form Login, Http Basic 인증 비활성화
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)

                // 세션을 사용하지 않고 Stateless로 설정
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Public / Private API 경로 설정
                .authorizeHttpRequests(auth -> auth
                        // Public API (회원가입, 스웨거 등) {온보딩 부분은 아직 jwt 구현 후 private 처리 예정}
                        .requestMatchers("/api/users/join","/api/users/{userId}/initial-info", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        // Private API (그 외 모든 요청은 인증 필요)
                        .anyRequest().authenticated()
                )

                // 인증/인가 실패 예외 핸들링 통일
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(authenticationEntryPoint) // 401
                        .accessDeniedHandler(accessDeniedHandler) // 403
                );

        return http.build();
    }
}