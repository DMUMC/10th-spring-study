package com.UmcSpringStudy.jingjing2.domain.user.converter;

import com.UmcSpringStudy.jingjing2.domain.user.dto.user.request.UserJoinRequest;
import com.UmcSpringStudy.jingjing2.domain.user.dto.user.response.UserProfileResponse;
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

    // 2. 프로필 조회: Entity -> Response (선호 음식 포함)
    public static UserProfileResponse toUserProfileResponse(User user) {
        return UserProfileResponse.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .point(user.getPoint())
                // User와 연관된 UserInterest 리스트에서 Interest의 이름(선호 음식)만 추출하여 리스트로 반환
                .interests(user.getUserInterests() != null ?
                        user.getUserInterests().stream()
                        .map(ui -> ui.getInterest().getContext()) // Interest 엔티티의 이름/내용 필드 (getContext() 혹은 getName() 등에 맞춰 사용)
                        .collect(Collectors.toList())
                        : null)
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
}