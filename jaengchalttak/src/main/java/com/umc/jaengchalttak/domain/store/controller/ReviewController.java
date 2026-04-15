package com.umc.jaengchalttak.domain.store.controller;

import com.umc.jaengchalttak.domain.store.dto.request.CommentRequestDTO;
import com.umc.jaengchalttak.domain.store.dto.request.StoreReviewRequestDTO;
import com.umc.jaengchalttak.domain.store.dto.response.StoreReviewListResponseDTO;
import com.umc.jaengchalttak.domain.store.payload.code.StoreSuccessCode;
import com.umc.jaengchalttak.global.apiPayload.ApiResponse;
import com.umc.jaengchalttak.global.apiPayload.code.BaseSuccessCode;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/store/review")
public class ReviewController {

    // ====== 가게 리뷰 목록 조회 ======
    @GetMapping
    public ApiResponse<List<StoreReviewListResponseDTO>> getStoreReviewList(@RequestParam("storeId") Long storeId,
                                                                            @RequestParam("page") int page) {
        // 임시값 삽입, Service 완성 시 삭제 예정
        List<StoreReviewListResponseDTO> result = List.of(
                StoreReviewListResponseDTO.builder()
                        .userId(1L)
                        .userName("홍길동")
                        .reviewStar(5)
                        .reviewContent("커피가 정말 맛있어요!")
                        .reviewCreatedAt(LocalDateTime.now())
                        .reviewSavePath(new String[]{"경로1", "경로2"})
                        .commentId(101L)
                        .commentContent("감사합니다 😊")
                        .commentCreateAt(LocalDateTime.now())
                        .build()
        );

        BaseSuccessCode code = StoreSuccessCode.REVIEW_LIST_OK;
        return ApiResponse.onSuccess(code, result);
    }


    // ====== 가게 리뷰 작성 ======
    @PostMapping
    public ApiResponse<String> writerReview(@RequestBody StoreReviewRequestDTO request) {
        BaseSuccessCode code = StoreSuccessCode.REVIEW_CREATED;
        return ApiResponse.onSuccess(code, "리뷰 작성 완료!");
    }


    // ====== 사장님 댓글 작성 ======
    @PostMapping("/comment")
    public ApiResponse<String> writerComment(@RequestBody CommentRequestDTO request) {
        BaseSuccessCode code = StoreSuccessCode.COMMENT_CREATED;
        return ApiResponse.onSuccess(code, "댓글 작성 완료!");
    }

}
