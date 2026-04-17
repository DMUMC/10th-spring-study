package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.mission.dto.MemberMissionResDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
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
}


