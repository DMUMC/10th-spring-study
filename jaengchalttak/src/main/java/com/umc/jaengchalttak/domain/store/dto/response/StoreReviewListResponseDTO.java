package com.umc.jaengchalttak.domain.store.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
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