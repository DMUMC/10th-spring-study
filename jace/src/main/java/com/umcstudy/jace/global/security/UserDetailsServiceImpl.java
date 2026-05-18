package com.umcstudy.jace.global.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    // JWT 필터에서 DB 조회 없이 직접 Authentication 생성하므로 이 메서드는 호출되지 않음
    // Spring Boot 자동 설정의 임시 계정 생성 방지용으로만 존재
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return new User(userId, "", Collections.emptyList());
    }
}
