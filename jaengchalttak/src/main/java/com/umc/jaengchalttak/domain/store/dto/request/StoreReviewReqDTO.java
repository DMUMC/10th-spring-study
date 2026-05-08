package com.umc.jaengchalttak.domain.store.dto.request;

import jakarta.validation.constraints.*;

public record StoreReviewReqDTO(
        @NotNull(message = "사용자 ID는 반드시 존재해야 합니다.") // Long은 NotNull 사용
        Long userId,

        @NotNull(message = "가게 ID는 반드시 존재해야 합니다.")
        Long storeId,

        @NotBlank(message = "리뷰 내용은 반드시 존재해야 합니다.") // 문자열은 NotBlank
        @Size(max = 500, message = "리뷰 내용은 500자를 넘기면 안됩니다.")
        String reviewContent,

        @NotNull(message = "리뷰 평점은 반드시 존재해야 합니다.")
        @DecimalMin(value = "1.0", message = "평점은 최소 1.0점 이상이어야 합니다.")
        @DecimalMax(value = "5.0", message = "평점은 최대 5.0점 이하여야 합니다.")
        Double reviewStar
) { }
