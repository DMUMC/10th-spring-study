package com.umcstudy.jace.domain.review.dto;

import java.util.List;

public class ReviewReqDTO {

    public record PostReviewWrite(
            String reviewContents,
            List<String> reviewImageUrl,
            float reviewScore
    ) {}
}
