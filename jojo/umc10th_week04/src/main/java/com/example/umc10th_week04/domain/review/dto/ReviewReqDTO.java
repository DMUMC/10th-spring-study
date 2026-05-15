package com.example.umc10th_week04.domain.review.dto;

public class ReviewReqDTO {

    public record CreateReview(
            Long userId,
            Long storeId,
            int score,
            String contents
    ) {
    }
}
