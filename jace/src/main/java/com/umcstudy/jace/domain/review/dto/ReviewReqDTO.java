package com.umcstudy.jace.domain.review.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public class ReviewReqDTO {

    public record PostReviewWrite(
            @NotBlank(message = "리뷰 내용은 필수입니다") @Size(max = 500, message = "리뷰 내용은 500자 이하여야 합니다") String reviewContents,
            List<String> reviewImageUrl,
            @DecimalMin(value = "0.5", message = "평점은 0.5 이상이어야 합니다") @DecimalMax(value = "5.0", message = "평점은 5.0 이하여야 합니다") float reviewScore
    ) {}
}
