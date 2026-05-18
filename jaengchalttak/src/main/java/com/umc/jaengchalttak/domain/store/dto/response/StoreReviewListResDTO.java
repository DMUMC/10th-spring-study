package com.umc.jaengchalttak.domain.store.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record StoreReviewListResDTO(
        Long userId,
        String userName,

        Long reviewId,
        Double reviewStar,
        String reviewContent,
        LocalDateTime reviewCreatedAt,

        Long commentId,
        String commentContent,
        LocalDateTime commentCreateAt
) { }