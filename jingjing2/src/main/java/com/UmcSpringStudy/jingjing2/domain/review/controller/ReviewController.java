package com.UmcSpringStudy.jingjing2.domain.review.controller;

import com.UmcSpringStudy.jingjing2.domain.review.dto.request.*;
import com.UmcSpringStudy.jingjing2.domain.review.dto.response.*;
import com.UmcSpringStudy.jingjing2.domain.review.service.ReviewService;
import com.UmcSpringStudy.jingjing2.global.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewservice;
    // 신규 리뷰 작성 (본문 및 첨부파일 관리 로직 포함)
    // json 과 파일이 섞여 있음에 따라 그냥 요청 시 오류 뜨고,
    // Content-Type 을 통해서 application/json, image/jpeg 명시해야 함
    @PostMapping("")
    public CommonResponse<ReviewResponse> createReview(
            @RequestBody @Valid ReviewCreateRequest request
            //@RequestPart(value = "request") @Valid ReviewCreateRequest request,
            //@RequestPart(value = "images", required = false) List<MultipartFile> images
    ) {
        return CommonResponse.success("리뷰 작성 성공", reviewservice.createReview(request));
    }

    // 리뷰 상세 조회 (본문, 이미지, 댓글 목록 통합)
    @GetMapping("/{reviewId}")
    public CommonResponse<ReviewResponse> getReview(@PathVariable Long reviewId) {

        return CommonResponse.success("리뷰 상세 조회 성공", null);
    }

    // 리뷰 수정
    @PatchMapping("/{reviewId}")
    public CommonResponse<ReviewResponse> updateReview(
            @PathVariable Long reviewId,
            @RequestBody @Valid ReviewUpdateRequest request) {

        return CommonResponse.success("리뷰 수정 성공", null);
    }

    // 리뷰 삭제
    @DeleteMapping("/{reviewId}")
    public CommonResponse<Void> deleteReview(@PathVariable Long reviewId) {

        return CommonResponse.success("리뷰 삭제 성공", null);
    }

    /* --- 리뷰 댓글 및 대댓글 관리 --- */

    // 특정 리뷰에 댓글 작성 (parentId 유무에 따라 대댓글로 처리)
    @PostMapping("/{reviewId}/comments")
    public CommonResponse<Void> createComment(
            @PathVariable Long reviewId,
            @RequestBody @Valid CommentRequest request) {

        return CommonResponse.success("댓글 작성 성공", null);
    }

    // 특정 댓글 수정
    @PatchMapping("/comments/{commentId}")
    public CommonResponse<Void> updateComment(
            @PathVariable Long commentId,
            @RequestBody @Valid CommentRequest request) {

        return CommonResponse.success("댓글 수정 성공", null);
    }

    // 특정 댓글 삭제
    @DeleteMapping("/comments/{commentId}")
    public CommonResponse<Void> deleteComment(@PathVariable Long commentId) {

        return CommonResponse.success("댓글 삭제 성공", null);
    }
}