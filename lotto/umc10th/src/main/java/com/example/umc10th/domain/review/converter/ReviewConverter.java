package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.mission.dto.MemberMissionResDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import java.util.List;
import lombok.Builder;

public class ReviewConverter {

    public static ReviewResDTO.GetInfo toGetInfo(
            Review review, String photoUrl
    ) {
        return ReviewResDTO.GetInfo.builder()
                .Id(review.getId())
                .storeName(review.getStore().getName())
                .content(review.getContent())
                .star(review.getStar().floatValue())
                .photoUrl(photoUrl)
                .build();
    }

    // ReviewConverter 클래스 내부
    public static ReviewResDTO.GetInfo toGetInfo(Review review) {
        return toGetInfo(review, null); // 또는 기본 URL 처리
    }

    public static <T> ReviewResDTO.Pagination<T> toPagination(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {
        return ReviewResDTO.Pagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }
}


