package com.umc.jaengchalttak.domain.inquiry.dto.response;

import java.time.LocalDateTime;

public record StoreReviewListResponseDTO(
        Long userId,
        String userName,

        Integer reviewStar,
        String reviewContent,
        LocalDateTime reviewCreatedAt,
        String[] reviewSavePath,

        Long commentId,
        String commentContent,
        LocalDateTime commentCreateAt
) { }