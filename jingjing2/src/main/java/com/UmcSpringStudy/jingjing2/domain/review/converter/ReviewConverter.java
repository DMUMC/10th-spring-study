package com.UmcSpringStudy.jingjing2.domain.review.converter;

import com.UmcSpringStudy.jingjing2.domain.review.entity.ReviewComment;
import com.UmcSpringStudy.jingjing2.domain.review.entity.StoreReview;
import com.UmcSpringStudy.jingjing2.domain.review.dto.request.ReviewCreateRequest;
import com.UmcSpringStudy.jingjing2.domain.review.dto.response.ReviewResponse;
import com.UmcSpringStudy.jingjing2.domain.review.dto.response.CommentResponse;
import com.UmcSpringStudy.jingjing2.domain.store.entity.Store;
import com.UmcSpringStudy.jingjing2.domain.user.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    //Request DTO -> StoreReview 엔티티 변환
    public static StoreReview toStoreReview(ReviewCreateRequest request, User user, Store store) {
        StoreReview review = new StoreReview();
        review.setTitle(request.getTitle());
        review.setContext(request.getContext());
        review.setRate(request.getRate());
        review.setUser(user);
        review.setStore(store);
        review.setPopularity(0); // 초기 인기도
        review.setCreatedDate(java.time.LocalDate.now()); // 현재 날짜 설정
        return review;
    }

    //StoreReview 엔티티 -> ReviewResponse 변환
    public static ReviewResponse toReviewResponse(StoreReview review, List<ReviewComment> comments) {
        return ReviewResponse.builder()
                .reviewId(review.getId())
                .title(review.getTitle())
                .context(review.getContext())
                .rate(review.getRate())
                .popularity(review.getPopularity())
                .createdDate(review.getCreatedDate())
                .userId(review.getUser().getId())
                .storeId(review.getStore().getId())
                .imageUrls(new ArrayList<>()) // 이미지 기능 확장 전까지 빈 리스트
                .comments(toCommentResponseList(comments)) // 댓글 리스트 변환
                .build();
    }

    //ReviewComment 리스트 -> CommentResponse 리스트 변환
    private static List<CommentResponse> toCommentResponseList(List<ReviewComment> comments) {
        if (comments == null) return new ArrayList<>();

        return comments.stream()
                .map(comment -> CommentResponse.builder()
                        .commentId(comment.getId())
                        .context(comment.getContext())
                        .date(comment.getDate())
                        .writerName("가게 주인") // 기획 의도(가게에서 답변) 반영
                        .childComments(new ArrayList<>()) // 현재 구조상 대댓글은 빈 리스트
                        .build())
                .collect(Collectors.toList());
    }
}