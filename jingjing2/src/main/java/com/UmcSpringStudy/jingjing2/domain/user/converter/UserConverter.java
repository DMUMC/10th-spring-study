package com.UmcSpringStudy.jingjing2.domain.user.converter;

import com.UmcSpringStudy.jingjing2.domain.user.dto.user.request.UserInitialInfoRequest;
import com.UmcSpringStudy.jingjing2.domain.user.dto.user.request.UserJoinRequest;
import com.UmcSpringStudy.jingjing2.domain.user.dto.user.response.UserProfileResponse;
import com.UmcSpringStudy.jingjing2.domain.user.entity.Autorication;
import com.UmcSpringStudy.jingjing2.domain.user.entity.Interest;
import com.UmcSpringStudy.jingjing2.domain.user.entity.User;
import com.UmcSpringStudy.jingjing2.domain.user.entity.UserInterest;
import com.UmcSpringStudy.jingjing2.domain.user.enums.Provider;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class UserConverter {

    // 1. 회원가입: Request -> Entity (초기 가입 단계 + 비밀번호 암호화)
    public static User toUser(UserJoinRequest request, PasswordEncoder passwordEncoder) {
        return User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .provider(Provider.LOCAL)
                .point(0)
                .cmCount(0)
                .created(LocalDate.now())
                .build();
    }

    // 2. 프로필 조회: Entity -> Response
    public static UserProfileResponse toUserProfileResponse(User user) {
        return UserProfileResponse.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .point(user.getPoint())
                .address(user.getAddress())
                .interests(user.getUserInterests() != null ?
                        user.getUserInterests().stream()
                        .map(ui -> ui.getInterest().getContext())
                        .collect(Collectors.toList())
                        : null)
                .locAllow(user.getAutorication() != null ? user.getAutorication().getLocAllow() : false)
                .adAllow(user.getAutorication() != null ? user.getAutorication().getAdAllow() : false)
                .build();
    }
    // 3. 관심사 매핑: User + Interest 목록 -> UserInterest 목록
    public static List<UserInterest> toUserInterestList(User user, List<Interest> interests) {
        return interests.stream()
                .map(interest -> UserInterest.builder()
                        .user(user)
                        .interest(interest)
                        .build())
                .collect(Collectors.toList());
    }
    // 4. 약관 동의 매핑: User + Request -> Autorication
    public static Autorication toAutorication(User user, UserInitialInfoRequest request) {
        return Autorication.builder()
                .user(user)
                .overFourteen(request.getOverFourteen())
                .termsOfService(request.getTermsOfService())
                .privacyPolicy(request.getPrivacyPolicy())
                .locAllow(request.getLocAllow())
                .adAllow(request.getAdAllow())
                .build();
    }
}