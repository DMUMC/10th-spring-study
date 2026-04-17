package com.umc.jaengchalttak.domain.store.dto.request;

public record StoreReviewReqDTO(
        Long userId,
        Long StoreId,
        String reviewContent,
        Double reviewStar,
        String[] photo
) { }
