package com.example.umc10th_week04.domain.review.converter;

import com.example.umc10th_week04.domain.mission.entity.Store;
import com.example.umc10th_week04.domain.review.dto.ReviewReqDTO;
import com.example.umc10th_week04.domain.review.dto.ReviewResDTO;
import com.example.umc10th_week04.domain.review.entity.Review;
import com.example.umc10th_week04.domain.user.entity.User;

import java.util.Collections;
import java.util.List;

public class ReviewConverter {

    private static final String DATE_PATTERN = "yyyy.MM.dd";

    public static Review toReview(ReviewReqDTO.CreateReview request, User user, Store store) {
        return Review.builder()
                .user(user)
                .store(store)
                .score(request.score())
                .contents(request.contents())
                .build();
    }

    public static ReviewResDTO.CreateReview toCreateReview(Review review) {
        return ReviewResDTO.CreateReview.builder()
                .reviewId(review.getId())
                .userId(review.getUser().getId())
                .storeId(review.getStore().getId())
                .score(review.getScore())
                .contents(review.getContents())
                .build();
    }

    public static ReviewResDTO.ReviewInfo toReviewInfo(Review review) {
        return ReviewResDTO.ReviewInfo.builder()
                .reviewId(review.getId())
                .storeName(review.getStore().getName())
                .score(review.getScore())
                .reviewContent(review.getContents())
                .pictures(Collections.emptyList())
                .createDate(formatCreateDate(review))
                .build();
    }

    public static ReviewResDTO.GetReview toGetReview(List<Review> reviews) {
        return ReviewResDTO.GetReview.builder()
                .reviews(reviews.stream()
                        .map(ReviewConverter::toReviewInfo)
                        .toList())
                .build();
    }

    public static ReviewResDTO.GetReview toGetReview() {
        return ReviewResDTO.GetReview.builder()
                .reviews(Collections.emptyList())
                .build();
    }

    private static String formatCreateDate(Review review) {
        if (review.getCreateAt() == null) {
            return null;
        }

        return review.getCreateAt().format(java.time.format.DateTimeFormatter.ofPattern(DATE_PATTERN));
    }

    // 페이지네이션 틀 생성
    public static <T> ReviewResDTO.Pagenation<T> toPagination(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {
        return ReviewResDTO.Pagenation.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }
}
