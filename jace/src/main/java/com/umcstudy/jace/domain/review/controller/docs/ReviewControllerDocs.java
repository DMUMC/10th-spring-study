package com.umcstudy.jace.domain.review.controller.docs;

import com.umcstudy.jace.domain.review.dto.ReviewReqDTO;
import com.umcstudy.jace.domain.review.dto.ReviewResDTO;
import com.umcstudy.jace.domain.review.enums.ReviewSortType;
import com.umcstudy.jace.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Review", description = "리뷰 관련 API")
public interface ReviewControllerDocs {

    @Operation(summary = "내 리뷰 목록 조회", description = "로그인한 사용자가 작성한 리뷰를 커서 기반 페이지네이션으로 조회합니다. ID 순 또는 별점 순으로 정렬할 수 있습니다.")
    ApiResponse<ReviewResDTO.GetMyReviews> getMyReviews(
            @Parameter(description = "정렬 기준 (ID: 최신순 / SCORE: 별점 높은 순)", example = "ID")
            ReviewSortType sortBy,
            @Parameter(description = "마지막으로 받은 리뷰 ID (첫 요청 시 생략)", example = "10")
            Long cursorId,
            @Parameter(description = "한 페이지에 조회할 리뷰 수", example = "10")
            int size
    );

    @Operation(summary = "가게 리뷰 목록 조회", description = "특정 가게의 리뷰를 커서 기반 페이지네이션으로 최신순 조회합니다. 비활성화된 리뷰는 제외됩니다.")
    ApiResponse<ReviewResDTO.GetReviews> getReviews(
            @Parameter(description = "조회할 가게 ID", required = true, example = "1")
            Long shopId,
            @Parameter(description = "마지막으로 받은 리뷰 ID (첫 요청 시 생략)", example = "10")
            Long cursorId,
            @Parameter(description = "한 페이지에 조회할 리뷰 수", example = "10")
            int size
    );

    @Operation(summary = "리뷰 작성", description = "특정 가게에 리뷰를 작성합니다. 별점은 0.5~5.0 사이, 내용은 500자 이하여야 합니다. 이미지 URL 목록은 선택 사항입니다.")
    ApiResponse<ReviewResDTO.PostReviewWrite> postReviewWrite(
            @Parameter(description = "리뷰를 작성할 가게 ID", required = true, example = "1")
            Long shopId,
            ReviewReqDTO.PostReviewWrite dto
    );
}
