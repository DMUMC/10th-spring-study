package com.UmcSpringStudy.jingjing2.global.config;

import com.UmcSpringStudy.jingjing2.global.security.jwt.JwtAuthenticationFilter;
import com.UmcSpringStudy.jingjing2.global.security.jwt.JwtProvider;
import com.UmcSpringStudy.jingjing2.global.security.CustomAccessDeniedHandler;
import com.UmcSpringStudy.jingjing2.global.security.CustomAuthenticationEntryPoint;
import com.UmcSpringStudy.jingjing2.global.security.oauth2.CustomOAuth2UserService;
import com.UmcSpringStudy.jingjing2.global.security.oauth2.OAuth2SuccessHandler;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationEntryPoint authenticationEntryPoint;
    private final CustomAccessDeniedHandler accessDeniedHandler;

    private final JwtProvider jwtProvider;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    public static final String[] PUBLIC_URLS = {
            "/api/users/join",
            "/api/users/login",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    };
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)

                // 세션을 사용하지 않고 Stateless로 설정
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Public / Private API 경로 설정
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PUBLIC_URLS).permitAll()
                        .anyRequest().authenticated()
                )

                //Auth2 소셜 로그인
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService) // 유저 정보 로드 (Upsert)
                        )
                        .successHandler(oAuth2SuccessHandler) // 로그인 성공 시 JWT 발급 핸들러
                )

                //JWT 검증 필터
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class)

                // 인증/인가 실패 예외 핸들링
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(authenticationEntryPoint) // 401
                        .accessDeniedHandler(accessDeniedHandler) // 403
                );

        return http.build();
    }
}