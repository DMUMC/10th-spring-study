package com.umcstudy.jace.domain.review.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Builder
    public record PostReviewWrite(
            Integer reviewId
    ) {}

    public record ReviewItem(
            Long reviewId,
            String userName,
            String reviewContent,
            BigDecimal reviewScore,
            LocalDateTime reviewContentTime,
            List<String> reviewImageUrls
    ) {}

    @Builder
    public record GetReviews(
            List<ReviewItem> reviewList,
            Boolean hasNext
    ) {}
}
