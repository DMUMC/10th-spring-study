package com.UmcSpringStudy.jingjing2.domain.user.service;

import com.UmcSpringStudy.jingjing2.domain.user.converter.UserConverter;
import com.UmcSpringStudy.jingjing2.domain.user.dto.user.request.*;
import com.UmcSpringStudy.jingjing2.domain.user.dto.user.response.UserProfileResponse;
import com.UmcSpringStudy.jingjing2.domain.user.entity.Autorication;
import com.UmcSpringStudy.jingjing2.domain.user.entity.Interest;
import com.UmcSpringStudy.jingjing2.domain.user.entity.User;
import com.UmcSpringStudy.jingjing2.domain.user.entity.UserInterest;
import com.UmcSpringStudy.jingjing2.domain.user.repository.InterestRepository;
import com.UmcSpringStudy.jingjing2.domain.user.repository.UserInterestRepository;
import com.UmcSpringStudy.jingjing2.domain.user.repository.UserRepository;
import com.UmcSpringStudy.jingjing2.global.exception.CustomException;
import com.UmcSpringStudy.jingjing2.global.exception.errorcodes.UserErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final InterestRepository interestRepository;
    private final UserInterestRepository userInterestRepository;
    private final PasswordEncoder passwordEncoder;

    // 1. 회원가입
    @Transactional
    public Long join(UserJoinRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new CustomException(UserErrorCode.DUPLICATE_EMAIL);
        }

        User user = UserConverter.toUser(request, passwordEncoder);

        return userRepository.save(user).getId();
    }

    // 2. 가입 후 최초 정보 설정 (온보딩)
    @Transactional
    public UserProfileResponse setInitialInfo(Long userId, UserInitialInfoRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));

        if (user.getUsername() != null || user.getBirth() != null || user.getPhone() != null) {
            throw new CustomException(UserErrorCode.ALREADY_ONBOARDED);
        }

        // 1) User 기본 정보 업데이트
        user.updateInitialInfo(
                request.getUsername(),
                request.getSex(),
                request.getBirth(),
                request.getPhone(),
                request.getAddress()
        );

        // 2) 약관 동의 (Autorication) 생성 및 매핑
        Autorication autorication = UserConverter.toAutorication(user, request);
        user.setAutorication(autorication);

        // 3) UserInterest 관심사 DB 데이터 생성
        if (request.getInterestIds() != null && !request.getInterestIds().isEmpty()) {
            List<Interest> interests = interestRepository.findAllById(request.getInterestIds());
            List<UserInterest> userInterests = UserConverter.toUserInterestList(user, interests);
            userInterestRepository.saveAll(userInterests);
        }

        return UserConverter.toUserProfileResponse(user);
    }

    // 3. 유저 프로필 조회
    public UserProfileResponse getUserProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));
        return UserConverter.toUserProfileResponse(user);
    }
}