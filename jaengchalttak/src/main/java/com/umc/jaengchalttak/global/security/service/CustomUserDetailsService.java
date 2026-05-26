package com.umc.jaengchalttak.global.security.service;

import com.umc.jaengchalttak.domain.user.entity.User;
import com.umc.jaengchalttak.domain.user.repository.UserRepository;
import com.umc.jaengchalttak.domain.user.enums.SocialProvider;
import com.umc.jaengchalttak.global.security.entity.AuthUser;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        return new AuthUser(user);
    }

    public UserDetails loadUserByUidAndSocialProvider(
            String provider,
            String socialUid) throws UsernameNotFoundException {
        User user = userRepository.findBySocialProviderAndSocialUid(SocialProvider.valueOf(provider), socialUid)
                .orElseThrow(() -> new UsernameNotFoundException("해당 UID와 로그인 경로(Provider)에 일치하는 사용자를 찾을 수 없습니다."));

        return new AuthUser(user);
    }

}
