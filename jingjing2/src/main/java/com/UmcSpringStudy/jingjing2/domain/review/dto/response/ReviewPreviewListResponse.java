package com.UmcSpringStudy.jingjing2.domain.review.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewPreviewListResponse {
    private List<ReviewResponse> reviewList;
    private Long nextCursor;     // 다음 조회를 위한 커서 (마지막 리뷰의 ID)
    private Boolean hasNext;     // 다음 페이지 존재 여부
}