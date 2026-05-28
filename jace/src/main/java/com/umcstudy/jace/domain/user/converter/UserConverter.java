package com.umcstudy.jace.domain.user.converter;

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
import java.util.List;

public class UserConverter {

    public static User toUser(UserReqDTO.PostSignup dto) {
        String email = (dto.email() != null && !dto.email().isBlank()) ? dto.email() : null;
        return User.builder()
                .name(dto.name())
                .email(email)
                .gender(dto.gender())
                .birth(dto.birth())
                .zipCode(dto.zipcode())
                .address(dto.address())
                .addressDetail(dto.addressDtl())
                .isQuit(false)
                .isChkPhone(false)
                .build();
    }

    public static User toUserFromForm(UserReqDTO.FormSignup dto, String encodedPassword) {
        return User.builder()
                .name(dto.name())
                .email(dto.email())
                .password(encodedPassword)
                .gender(dto.gender())
                .birth(dto.birth())
                .zipCode(dto.zipcode())
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

    public static UserSetting toUserSetting(User user) {
        return UserSetting.builder()
                .user(user)
                .newEventAlarm(false)
                .reviewAnswerAlarm(false)
                .inquiryAnswerAlarm(false)
                .build();
    }

    public static UserResDTO.PostSignup toPostSignupRes(User user, String accessToken, String refreshToken) {
        return UserResDTO.PostSignup.builder()
                .userId(user.getId().intValue())
                .name(user.getName())
                .createdAt(LocalDate.now())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .build();
    }

    public static UserResDTO.GetTerms toGetTerms(List<Term> terms) {
        return UserResDTO.GetTerms.builder()
                .termsList(terms.stream()
                        .map(term -> new UserResDTO.TermsItem(
                                term.getId().intValue(),
                                term.getTermsTitle(),
                                term.getTermsContent(),
                                term.getIsRequired()
                        ))
                        .toList())
                .build();
    }

    public static UserResDTO.GetFoods toGetFoods(List<Food> foods) {
        return UserResDTO.GetFoods.builder()
                .foodsList(foods.stream()
                        .map(food -> new UserResDTO.FoodsItem(
                                food.getId().intValue(),
                                food.getFoodName()
                        ))
                        .toList())
                .build();
    }

    public static UserResDTO.GetMyPage toGetMyPage(User user, int pointBalance) {
        String email = user.getEmail();
        boolean isRelayEmail = email != null && email.endsWith("@privaterelay.appleid.com");
        return UserResDTO.GetMyPage.builder()
                .name(user.getName())
                .email(email)
                .isRelayEmail(isRelayEmail)
                .pointBalance(pointBalance)
                .build();
    }
}
