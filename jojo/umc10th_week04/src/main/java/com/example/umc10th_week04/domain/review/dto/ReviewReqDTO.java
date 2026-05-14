package com.example.umc10th_week04.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class ReviewReqDTO {

    public record CreateReview(
            Long userId,
            Long storeId,

            @Min(value = 1, message = "리뷰 점수는 1점 이상이어야 합니다.")
            @Max(value = 5, message = "리뷰 점수는 5점 이하여야 합니다.")
            Integer score,

            String contents
    ) {
    }
}
