package com.umcstudy.jace.domain.review.converter;

import com.umcstudy.jace.domain.mission.entity.Shop;
import com.umcstudy.jace.domain.review.dto.ReviewReqDTO;
import com.umcstudy.jace.domain.review.dto.ReviewResDTO;
import com.umcstudy.jace.domain.review.entity.Review;
import com.umcstudy.jace.domain.review.entity.mapping.ReviewImage;
import com.umcstudy.jace.domain.user.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewConverter {

    public static Review toReview(Shop shop, User user, ReviewReqDTO.PostReviewWrite dto) {
        return Review.builder()
                .shop(shop)
                .user(user)
                .reviewContent(dto.reviewContents())
                .reviewContentTime(LocalDateTime.now())
                .reviewScore(BigDecimal.valueOf(dto.reviewScore()))
                .isDisabled(false)
                .build();
    }

    public static ReviewImage toReviewImage(Review review, String imageUrl) {
        return ReviewImage.builder()
                .review(review)
                .reviewImageUrl(imageUrl)
                .build();
    }

    public static ReviewResDTO.PostReviewWrite toPostReviewWrite(Review review) {
        return ReviewResDTO.PostReviewWrite.builder()
                .reviewId(review.getId().intValue())
                .build();
    }

    public static ReviewResDTO.ReviewItem toReviewItem(Review review) {
        List<String> imageUrls = review.getReviewImages().stream()
                .map(ReviewImage::getReviewImageUrl)
                .toList();

        return ReviewResDTO.ReviewItem.builder()
                .reviewId(review.getId())
                .userName(review.getUser().getName())
                .reviewContent(review.getReviewContent())
                .reviewScore(review.getReviewScore())
                .reviewContentTime(review.getReviewContentTime())
                .reviewImageUrls(imageUrls)
                .build();
    }

    public static ReviewResDTO.GetReviews toGetReviews(List<ReviewResDTO.ReviewItem> reviewList, boolean hasNext) {
        return ReviewResDTO.GetReviews.builder()
                .reviewList(reviewList)
                .hasNext(hasNext)
                .build();
    }

    public static ReviewResDTO.GetMyReviews toGetMyReviews(List<ReviewResDTO.ReviewItem> reviewList, boolean hasNext) {
        return ReviewResDTO.GetMyReviews.builder()
                .reviewList(reviewList)
                .hasNext(hasNext)
                .build();
    }
}
