package com.umc.jaengchalttak.domain.store.dto.request;

public record StoreReviewReqDTO(
        Long userId,
        Long storeId,
        String reviewContent,
        Double reviewStar
) { }
