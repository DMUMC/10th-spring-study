package com.umc.jaengchalttak.domain.store.controller;

import com.umc.jaengchalttak.domain.inquiry.dto.response.InquiryInfoResponseDTO;
import com.umc.jaengchalttak.domain.inquiry.dto.response.StoreReviewListResponseDTO;
import com.umc.jaengchalttak.domain.store.dto.request.CommentRequestDTO;
import com.umc.jaengchalttak.domain.store.dto.request.StoreReviewRequestDTO;
import com.umc.jaengchalttak.domain.store.dto.response.AreaStoreListResponseDTO;
import com.umc.jaengchalttak.domain.store.payload.code.StoreSuccessCode;
import com.umc.jaengchalttak.global.apiPayload.ApiResponse;
import com.umc.jaengchalttak.global.apiPayload.code.BaseSuccessCode;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    // ====== 현재 지역 내 가게 조회 ======
    @GetMapping
    public ApiResponse<List<AreaStoreListResponseDTO>> searchAreaListStore(@RequestParam("address") String address,
                                                                           @RequestParam("page") int page) {
        // 임시값 삽입, Service 완성 시 삭제 예정
        List<AreaStoreListResponseDTO> result = List.of(
                new AreaStoreListResponseDTO(1L, 500, "스타벅스 강남점", "서울 강남구 테헤란로 123", "카페", "경로"),
                new AreaStoreListResponseDTO(2L, 700, "투썸플레이스 홍대점", "서울 마포구 홍익로 45", "카페", "경로")
        );

        BaseSuccessCode code = StoreSuccessCode.STORE_LIST_OK;
        return ApiResponse.onSuccess(code, result);
    }


    // ====== 가게 정보 상세 조회 ======
    @GetMapping("{storeId}")
    public ApiResponse<InquiryInfoResponseDTO> getStoreInfo(@PathVariable Long storeId) {
        // 임시값 삽입, Service 완성 시 삭제 예정
        InquiryInfoResponseDTO result = new InquiryInfoResponseDTO(
                "매장 이용 문의",
                "스타벅스 강남점 이용 관련 문의입니다.",
                "STORE",
                new String[]{"경로", "경로2"},
                LocalDateTime.now(),
                true
        );

        BaseSuccessCode code = StoreSuccessCode.STORE_INFO_OK;
        return ApiResponse.onSuccess(code, result);
    }

    // ====== 가게 리뷰 목록 조회 ======
    @GetMapping("/review")
    public ApiResponse<List<StoreReviewListResponseDTO>> getStoreReviewList(@RequestParam("storeId") Long storeId,
                                                                            @RequestParam("page") int page) {
        // 임시값 삽입, Service 완성 시 삭제 예정
        List<StoreReviewListResponseDTO> result = List.of(
                new StoreReviewListResponseDTO(
                        1L,
                        "홍길동",
                        5,
                        "커피가 정말 맛있어요!",
                        LocalDateTime.now(),
                        new String[]{"경로1", "경로2"},
                        101L,
                        "감사합니다 😊",
                        LocalDateTime.now()
                ),
                new StoreReviewListResponseDTO(
                        2L,
                        "김철수",
                        4,
                        "분위기가 좋아요.",
                        LocalDateTime.now().minusDays(1),
                        new String[]{"경로3"},
                        102L,
                        "다음에도 방문해주세요!",
                        LocalDateTime.now().minusDays(1)
                )
        );

        BaseSuccessCode code = StoreSuccessCode.REVIEW_LIST_OK;
        return ApiResponse.onSuccess(code, result);
    }


    // ====== 가게 리뷰 작성 ======
    @PostMapping("/review")
    public ApiResponse<String> writerReview(@RequestBody StoreReviewRequestDTO request) {
        BaseSuccessCode code = StoreSuccessCode.REVIEW_CREATED;
        return ApiResponse.onSuccess(code, "리뷰 작성 완료!");
    }

    // ====== 사장님 댓글 작성 ======
    @PostMapping("/review/comment")
    public ApiResponse<String> writerComment(@RequestBody CommentRequestDTO request) {
        BaseSuccessCode code = StoreSuccessCode.COMMENT_CREATED;
        return ApiResponse.onSuccess(code, "댓글 작성 완료!");
    }

}
