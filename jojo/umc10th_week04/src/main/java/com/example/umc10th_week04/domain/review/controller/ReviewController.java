package com.example.umc10th_week04.domain.review.controller;

import com.example.umc10th_week04.domain.review.dto.ReviewReqDTO;
import com.example.umc10th_week04.domain.review.dto.ReviewResDTO;
import com.example.umc10th_week04.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th_week04.domain.review.service.ReviewService;
import com.example.umc10th_week04.global.apiPayload.ApiResponse;
import com.example.umc10th_week04.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/v1/reviews/{storeId}")
    public ApiResponse<ReviewResDTO.CreateReview> createReview(
            @PathVariable Long storeId,
            @Valid @RequestBody ReviewReqDTO.CreateReview request
    ) {
        BaseSuccessCode code = ReviewSuccessCode.CREATED;
        return ApiResponse.onSuccess(code, reviewService.createReview(storeId, request));
    }

    @GetMapping("/v1/users/reviews")
    public ApiResponse<ReviewResDTO.Pagenation<ReviewResDTO.ReviewInfo>> getMyReviews(
            @Valid @RequestBody ReviewReqDTO.UserId dto,
            @RequestParam Integer pageSize,
            @RequestParam String cursor,
            @RequestParam String query
    ) {
        BaseSuccessCode code = ReviewSuccessCode.OK;
        return ApiResponse.onSuccess(code, reviewService.getMyReviews(dto.userId(), pageSize, cursor, query));
    }

    @GetMapping
    public ApiResponse<ReviewResDTO.GetReview> getReview() {
        BaseSuccessCode code = ReviewSuccessCode.OK;

        return ApiResponse.onSuccess(code, reviewService.getReview());
    }
}
