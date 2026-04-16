package com.umcstudy.jace.domain.review.dto;

import lombok.Builder;

public class ReviewResDTO {

    @Builder
    public record PostReviewWrite(
            Integer reviewId
    ) {}
}
