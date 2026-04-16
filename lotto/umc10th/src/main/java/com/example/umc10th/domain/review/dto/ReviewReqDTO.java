package com.example.umc10th.domain.review.dto;

import com.example.umc10th.domain.member.entity.Member;

public class ReviewReqDTO {
    public record GetInfo(
            Member memberId
    ) {}

}
