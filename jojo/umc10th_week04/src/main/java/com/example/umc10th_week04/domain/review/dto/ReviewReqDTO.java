package com.example.umc10th_week04.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ReviewReqDTO {

    public record CreateReview(

            @NotNull(message = "유저 아이디는 필수 입니다.")
            Long userId,

            @NotNull(message = "가게 아이디는 필수 입니다.")
            Long storeId,

            @NotNull(message = "리뷰 점수는 필수 입니다.")
            @Min(value = 1, message = "리뷰 점수는 1점 이상이어야 합니다.")
            @Max(value = 5, message = "리뷰 점수는 5점 이하여야 합니다.")
            Integer score,

            @NotNull(message = "리뷰 내용은 필수 입니다.")
            String contents
    ) {}

    public record UserId(

            @NotNull(message = "유저 아이디는 필수 입니다.")
            Long userId
    ){}
}
