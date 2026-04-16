package com.umcstudy.jace.domain.user.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class UserResDTO {

    @Builder
    public record PostSignup(
            Integer userId,
            String name,
            LocalDate createdAt
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
}
