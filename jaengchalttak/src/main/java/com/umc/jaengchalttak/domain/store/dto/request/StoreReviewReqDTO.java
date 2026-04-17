package com.umc.jaengchalttak.domain.store.dto.request;

import java.util.List;

public record StoreReviewReqDTO(
        Long userId,
        Long StoreId,
        String reviewContent,
        Double reviewStar,
        List<String> photo
) { }
