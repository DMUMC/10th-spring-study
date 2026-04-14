package com.umc.jaengchalttak.domain.store.dto.request;

public record StoreReviewRequestDTO(
        Long userId,
        Long StoreId,
        String reviewContent,
        Double reviewStar,
        String[] photo
) { }
