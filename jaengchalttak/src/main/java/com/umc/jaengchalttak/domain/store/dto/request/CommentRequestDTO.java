package com.umc.jaengchalttak.domain.store.dto.request;

public record CommentRequestDTO (
        Long reviewId,
        String commentContent
) { }
