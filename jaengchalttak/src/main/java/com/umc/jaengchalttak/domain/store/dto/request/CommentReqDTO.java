package com.umc.jaengchalttak.domain.store.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CommentReqDTO(
        @NotNull(message = "리뷰 ID는 필수입니다.")
        Long reviewId,

        @NotBlank(message = "댓글 내용은 필수입니다.")
        @Size(max = 500, message = "댓글 내용은 500자 이내여야 합니다.")
        String commentContent
) { }
