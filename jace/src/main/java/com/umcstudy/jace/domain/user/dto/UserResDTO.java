package com.umcstudy.jace.domain.user.dto;

import com.umcstudy.jace.domain.user.enums.SocialProvider;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class UserResDTO {

    @Builder
    public record PostSignup(
            Integer userId,
            String name,
            LocalDate createdAt,
            String accessToken,
            String tokenType
    ){}

    @Builder
    public record GetMyPage(
            String name,
            String email,        // Apple 이메일 숨기기 선택 시 null
            Boolean isRelayEmail, // Apple 중계 이메일 여부 (xxx@privaterelay.appleid.com)
            Integer pointBalance
    ){}

    public record TermsItem(
            Integer termsId,
            String termsName,
            String termsContent,
            Boolean isRequired
    ) {}

    @Builder
    public record GetTerms(
            List<TermsItem> termsList
    ){}

    public record FoodsItem(
            Integer foodId,
            String foodName
    ) {}

    @Builder
    public record GetFoods(
            List<FoodsItem> foodsList
    ){}

    public record SocialLogin(
            Boolean isNewUser,
            String accessToken,
            String tokenType,
            String email,
            String name,
            SocialProvider provider,
            String providerUserId
    ) {}
}
