package com.UmcSpringStudy.jingjing2.domain.user.converter;

import com.UmcSpringStudy.jingjing2.domain.user.dto.user.request.UserJoinRequest;
import com.UmcSpringStudy.jingjing2.domain.user.dto.user.response.UserProfileResponse;
import com.UmcSpringStudy.jingjing2.domain.user.entity.User;
import com.UmcSpringStudy.jingjing2.domain.user.enums.Provider;

import java.time.LocalDate;
import java.util.stream.Collectors;

public class UserConverter {

    // 1. 회원가입: Request -> Entity (초기 가입 단계)
    public static User toUser(UserJoinRequest request) {
        return User.builder()
                .email(request.getEmail())
                .password(request.getPassword()) // 실제 서비스에선 PasswordEncoder 사용 권장
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
                .interests(user.getUserInterests().stream()
                        .map(ui -> ui.getInterest().getContext())
                        .collect(Collectors.toList()))
                .build();
    }
}