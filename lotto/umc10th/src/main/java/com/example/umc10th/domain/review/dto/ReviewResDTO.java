package com.example.umc10th.domain.review.dto;

import lombok.Builder;

public class ReviewResDTO {

    @Builder
    public record GetInfo(
            Long Id,
            String storeName,
            String content,
            Float star,
            String photoUrl
    ){

    }
}
