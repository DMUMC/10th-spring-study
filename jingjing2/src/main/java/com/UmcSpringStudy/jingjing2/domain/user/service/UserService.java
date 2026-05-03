package com.UmcSpringStudy.jingjing2.domain.user.service;

import com.UmcSpringStudy.jingjing2.domain.user.converter.UserConverter;
import com.UmcSpringStudy.jingjing2.domain.user.dto.user.request.UserInitialInfoRequest;
import com.UmcSpringStudy.jingjing2.domain.user.dto.user.request.UserJoinRequest;
import com.UmcSpringStudy.jingjing2.domain.user.dto.user.response.UserProfileResponse;
import com.UmcSpringStudy.jingjing2.domain.user.entity.User;
import com.UmcSpringStudy.jingjing2.domain.user.repository.UserRepository;
import com.UmcSpringStudy.jingjing2.global.exception.CustomException;
import com.UmcSpringStudy.jingjing2.global.exception.errorcodes.UserErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long join(UserJoinRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new CustomException(UserErrorCode.DUPLICATE_EMAIL);
        }
        User user = UserConverter.toUser(request);
        return userRepository.save(user).getId();
    }

    @Transactional
    public UserProfileResponse setInitialInfo(Long userId, UserInitialInfoRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));

        user.setUsername(request.getUsername());
        user.setSex(request.getSex());
        user.setBirth(request.getBirth());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());

        return UserConverter.toUserProfileResponse(user);
    }

    public UserProfileResponse getUserProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));

        return UserConverter.toUserProfileResponse(user);
    }
}