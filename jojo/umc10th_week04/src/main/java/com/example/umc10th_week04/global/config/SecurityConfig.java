package com.example.umc10th_week04.global.config;

import com.example.umc10th_week04.global.security.handler.CustomAccessDenied;
import com.example.umc10th_week04.global.security.handler.CustomEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// Spring Security 설정 활성화
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final String [] publicUris = {
            "/auth/users/sign-up"
    };

    private final String [] swaggerUris = {
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/v3/api-docs/**",
            "/swagger-resources/**",
    };

    // SecurityFilterChain 정의 및 HttpSecurity 객체를 통한 여러 보안 설정 구성
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)

                // HTTP 요청에 대한 접근 제어 설정
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(swaggerUris).permitAll()
                        .requestMatchers(publicUris).permitAll()

                        // 인가 실패 테스트용
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )

                // 폼 로그인에 대한 설정
                // 성공 시 /swagger-ui/index.html 로 리다이렉트, alwaysUse를 true로 설정 시 로그인 성공 시 항상 Swagger로 리다이렉트
                // 로그인 페이지는 모든 사용자가 접근 가능하도록 설정
                .formLogin(form -> form
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/swagger-ui.html", true)
                        .permitAll()
                )

                // 로그아웃처리에 대한 설정
                // /logout 경로로 로그아웃을 처리
                // 로그아웃 성공 시 /login?logout으로 리다이렉트
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )

                // 예외 상황 핸들러
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(customAccessDenied())
                        .authenticationEntryPoint(customEntryPoint())
                )
        ;

        return http.build();
    }

    // 비밀번호 솔트를 위한 BCrypt를 PasswordEncoder로 설정 가능
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomAccessDenied customAccessDenied() {
        return new CustomAccessDenied();
    }

    @Bean
    public CustomEntryPoint customEntryPoint() {
        return new CustomEntryPoint();
    }
}
