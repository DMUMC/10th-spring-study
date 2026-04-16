package com.example.umc10th_week04.domain.review.dto;

import lombok.Builder;

import java.util.List;

public class ReviewResDTO {

    @Builder
    public record GetReview(
            List<ReviewInfo> reviews
    ){}

    @Builder
    public record ReviewInfo(
            Long reviewId,
            String storeName,
            Double score,
            String reviewContent,
            List<PictureInfo> pictures,
            String createDate
    ){}

    @Builder
    public record PictureInfo(
            String pictureUrl
    ){}
}
