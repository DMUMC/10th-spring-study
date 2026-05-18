package com.example.umc10th_week04.domain.review.dto;

import lombok.Builder;

import java.util.List;

public class ReviewResDTO {

    @Builder
    public record GetReview(
            List<ReviewInfo> reviews
    ){}

    @Builder
    public record CreateReview(
            Long reviewId,
            Long userId,
            Long storeId,
            Integer score,
            String contents
    ){}

    @Builder
    public record ReviewInfo(
            Long reviewId,
            String storeName,
            Integer score,
            String reviewContent,
            List<PictureInfo> pictures,
            String createDate
    ){}

    @Builder
    public record PictureInfo(
            String pictureUrl
    ){}

    @Builder
    public record Pagenation<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {}
}
