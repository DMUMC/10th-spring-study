package com.umc.jaengchalttak.domain.user.service;

import com.umc.jaengchalttak.domain.user.converter.UserConverter;
import com.umc.jaengchalttak.domain.user.dto.UserInfoDTO;
import com.umc.jaengchalttak.domain.user.entity.User;
import com.umc.jaengchalttak.domain.user.payload.UserException;
import com.umc.jaengchalttak.domain.user.payload.code.UserErrorCode;
import com.umc.jaengchalttak.domain.user.repository.ServiceUseAllowRepository;
import com.umc.jaengchalttak.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserInfoDTO findUserInfo(Long userId) {
        // 사용자 약관 동의 여부도 같이
        User user = userRepository.findByIdWithServiceUseAllow(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        return UserConverter.toUserInfoDTO(user);
    }

}
