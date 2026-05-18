package com.umcstudy.jace.domain.review.controller;

import com.umcstudy.jace.domain.review.dto.ReviewReqDTO;
import com.umcstudy.jace.domain.review.dto.ReviewResDTO;
import com.umcstudy.jace.domain.review.enums.ReviewSortType;
import com.umcstudy.jace.domain.review.exception.code.ReviewSuccessCode;
import com.umcstudy.jace.domain.review.service.ReviewService;
import com.umcstudy.jace.global.apiPayload.ApiResponse;
import com.umcstudy.jace.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/users/me/reviews")
    public ApiResponse<ReviewResDTO.GetMyReviews> getMyReviews(
            @RequestParam(defaultValue = "ID") ReviewSortType sortBy,
            @RequestParam(required = false) Long cursorId,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.MY_REVIEW_OK,
                reviewService.getMyReviews(sortBy, cursorId, size));
    }

    @GetMapping("shops/{shopId}/reviews")
    public ApiResponse<ReviewResDTO.GetReviews> getReviews(
            @PathVariable Long shopId,
            @RequestParam(required = false) Long cursorId,
            @RequestParam(defaultValue = "10") int size
    ){
        BaseSuccessCode code = ReviewSuccessCode.GET_OK;
        return ApiResponse.onSuccess(code, reviewService.getReviews(shopId, cursorId, size));
    }

    @PostMapping("shops/{shopId}/reviews")
    public ApiResponse<ReviewResDTO.PostReviewWrite> postReviewWrite(
            @PathVariable Long shopId,
            @Valid @RequestBody ReviewReqDTO.PostReviewWrite dto
    ){
        BaseSuccessCode code = ReviewSuccessCode.OK;
        return ApiResponse.onSuccess(code, reviewService.postReviewWrite(dto, shopId));
    }
}
