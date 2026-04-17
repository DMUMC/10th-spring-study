package com.umc.jaengchalttak.domain.store.dto.request;

public record CommentReqDTO(
        Long reviewId,
        String commentContent
) { }
