package com.example.umc10th_week04.global.security.service;

import com.example.umc10th_week04.domain.user.entity.User;
import com.example.umc10th_week04.domain.user.enums.SocialType;
import com.example.umc10th_week04.domain.user.exception.UserException;
import com.example.umc10th_week04.domain.user.exception.code.UserErrorCode;
import com.example.umc10th_week04.domain.user.repository.UserRepository;
import com.example.umc10th_week04.global.security.entity.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService {

    private final UserRepository userRepository;

    public UserDetails loadUserByUidAndSocialType(
            SocialType socialType,
            String username
    ) throws UsernameNotFoundException {
        User user = userRepository.findBySocialTypeAndSocialUid(socialType, username)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
        return new AuthUser(user);
    }
}
