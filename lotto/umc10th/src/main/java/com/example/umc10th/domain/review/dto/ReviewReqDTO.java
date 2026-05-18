package com.example.umc10th.domain.review.dto;

import com.example.umc10th.domain.member.entity.Member;
import jakarta.validation.constraints.NotNull;

public class ReviewReqDTO {
    public record GetInfo(
        @NotNull(message = "멤버 아이디 입력은 필수입니다.")
            Long id
    ) {}

}
