package com.umcstudy.jace.domain.user.converter;

import com.umcstudy.jace.domain.point.entity.Point;
import com.umcstudy.jace.domain.user.dto.UserReqDTO;
import com.umcstudy.jace.domain.user.dto.UserResDTO;
import com.umcstudy.jace.domain.user.entity.Food;
import com.umcstudy.jace.domain.user.entity.Term;
import com.umcstudy.jace.domain.user.entity.User;
import com.umcstudy.jace.domain.user.entity.UserSetting;
import com.umcstudy.jace.domain.user.entity.mapping.UserFood;
import com.umcstudy.jace.domain.user.entity.mapping.UserSocial;
import com.umcstudy.jace.domain.user.entity.mapping.UserTerm;

import java.time.LocalDate;

public class UserConverter {

    public static User toUser(UserReqDTO.PostSignup dto) {
        return User.builder()
                .name(dto.name())
                .email(dto.email())
                .gender(dto.gender())
                .birth(dto.birth())
                .zipCode(String.valueOf(dto.zipcode()))
                .address(dto.address())
                .addressDetail(dto.addressDtl())
                .isQuit(false)
                .isChkPhone(false)
                .build();
    }

    public static UserSocial toUserSocial(User user, UserReqDTO.PostSignup dto) {
        return UserSocial.builder()
                .user(user)
                .provider(dto.socialProvider())
                .providerUserId(dto.socialId())
                .build();
    }

    public static UserTerm toUserTerm(User user, Term term, Boolean isAgree) {
        return UserTerm.builder()
                .user(user)
                .term(term)
                .isAgree(isAgree)
                .build();
    }

    public static UserFood toUserFood(User user, Food food) {
        return UserFood.builder()
                .user(user)
                .food(food)
                .build();
    }

    public static Point toPoint(User user) {
        return Point.builder()
                .user(user)
                .pointBalance(0)
                .build();
    }

    public static UserSetting toUserSetting(User user) {
        return UserSetting.builder()
                .user(user)
                .newEventAlarm(false)
                .reviewAnswerAlarm(false)
                .inquiryAnswerAlarm(false)
                .build();
    }

    public static UserResDTO.PostSignup toPostSignupRes(User user, String token) {
        return UserResDTO.PostSignup.builder()
                .userId(user.getId().intValue())
                .name(user.getName())
                .createdAt(LocalDate.now())
                .accessToken(token)
                .tokenType("Bearer")
                .build();
    }

    public static UserResDTO.GetMyPage toGetMyPage(User user, int pointBalance) {
        return UserResDTO.GetMyPage.builder()
                .name(user.getName())
                .email(user.getEmail())
                .pointBalance(pointBalance)
                .build();
    }
}
