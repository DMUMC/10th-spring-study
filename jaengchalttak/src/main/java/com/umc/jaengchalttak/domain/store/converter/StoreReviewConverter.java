package com.umc.jaengchalttak.domain.store.converter;

import com.umc.jaengchalttak.domain.store.dto.request.StoreReviewReqDTO;
import com.umc.jaengchalttak.domain.store.dto.response.StoreReviewListResDTO;
import com.umc.jaengchalttak.domain.store.entity.OwnerComment;
import com.umc.jaengchalttak.domain.store.entity.Store;
import com.umc.jaengchalttak.domain.store.entity.StoreReview;
import com.umc.jaengchalttak.domain.user.entity.User;
import com.umc.jaengchalttak.global.apiPayload.code.GeneralErrorCode;
import com.umc.jaengchalttak.global.apiPayload.exception.ProjectException;

public class StoreReviewConverter {

    // 객체 생성하면 예외
    private StoreReviewConverter() {
        throw new ProjectException(GeneralErrorCode.UTILITY_CLASS_INSTANTIATION);
    }

    public static StoreReviewListResDTO toStoreReviewListResDTO(StoreReview storeReview) {
        OwnerComment ownerComment = storeReview.getOwnerComment();

        return StoreReviewListResDTO.builder()
                .userId(storeReview.getUser().getId())
                .userName(storeReview.getUser().getName())
                .reviewId(storeReview.getId())
                .reviewStar(storeReview.getReviewStar())
                .reviewContent(storeReview.getReviewContent())
                .reviewCreatedAt(storeReview.getCreatedAt())
                .commentId(ownerComment != null ? ownerComment.getId() : null)
                .commentContent(ownerComment != null ? ownerComment.getCommentContent() : null)
                .commentCreateAt(ownerComment != null ? ownerComment.getCreatedAt() : null)
                .build();
    }

    public static StoreReview toStoreReview(StoreReviewReqDTO request, User user, Store store) {
        return StoreReview.builder()
                .user(user)
                .store(store)
                .reviewStar(request.reviewStar())
                .reviewContent(request.reviewContent())
                .build();
    }

}
