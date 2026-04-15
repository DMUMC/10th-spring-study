package com.umc.jaengchalttak.domain.inquiry.controller;

import com.umc.jaengchalttak.domain.inquiry.dto.request.SubmitInquiryReqDTO;
import com.umc.jaengchalttak.domain.inquiry.dto.response.InquiryInfoResDTO;
import com.umc.jaengchalttak.domain.inquiry.dto.response.InquiryListResDTO;
import com.umc.jaengchalttak.domain.inquiry.payload.code.InquirySuccessCode;
import com.umc.jaengchalttak.global.apiPayload.ApiResponse;
import com.umc.jaengchalttak.global.apiPayload.code.BaseSuccessCode;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/inquiry")
public class InquiryController {

    // ====== 유저 1대1 문의 목록 조회 ======
    @GetMapping
    public ApiResponse<List<InquiryListResDTO>> getInquiryList(@RequestParam("userId") Long userId,
                                                               @RequestParam("page") int page) {
        // 임시값 삽입, Service 완성 시 삭제 예정
        List<InquiryListResDTO> result = List.of(
                InquiryListResDTO.builder()
                        .inquiryId(1L)
                        .inquiryTitle("배송 문의")
                        .createdAt(LocalDateTime.now())
                        .isInquiryWait(true)
                        .build()
        );

        BaseSuccessCode code = InquirySuccessCode.LIST_OK;
        return ApiResponse.onSuccess(code, result);
    }


    // ====== 유저 1대1 문의 상세 정보 조회 ======
    @GetMapping("{inquiryId}")
    public ApiResponse<InquiryInfoResDTO> getInquiryInfo(@PathVariable Long inquiryId) {

        // 임시값 삽입, Service 완성 시 삭제 예정
        InquiryInfoResDTO result = InquiryInfoResDTO.builder()
                .inquiryTitle("배송이 아직 안 왔어요")
                .inquiryContent("3일 전에 주문했는데 배송이 시작되지 않았습니다.")
                .inquiryType("DELIVERY")
                .reviewSavePath(new String[]{"경로1", "경로2"})
                .createdAt(LocalDateTime.now())
                .isInquiryWait(true)
                .build();

        BaseSuccessCode code = InquirySuccessCode.INFO_OK;
        return ApiResponse.onSuccess(code, result);
    }


    // ====== 1대1 문의 제출 ======
    @PostMapping
    public ApiResponse<String> submitInquiry(@RequestBody SubmitInquiryReqDTO request) {
        BaseSuccessCode code = InquirySuccessCode.SUBMIT_INQUIRY_CREATED;
        return ApiResponse.onSuccess(code, "1대1 문의 제출 성공!");
    }

}
