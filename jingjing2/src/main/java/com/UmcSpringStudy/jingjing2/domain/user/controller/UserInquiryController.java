package com.UmcSpringStudy.jingjing2.domain.user.controller;


import com.UmcSpringStudy.jingjing2.domain.user.dto.inquiry.request.UserInquiryUpdateRequest;
import com.UmcSpringStudy.jingjing2.domain.user.dto.inquiry.response.*;
import com.UmcSpringStudy.jingjing2.global.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/{userId}/inquiries")
public class UserInquiryController {

    // 특정 유저 문의 내역 출력 (페이징 포함)
    @GetMapping
    public CommonResponse<UserInquiryPreviewResponse> getInquiries(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page) {
        return CommonResponse.success("문의 내역 조회 성공", null);
    }

    // 특정 유저 문의 내역 수정
    @PatchMapping("/{inquiryId}")
    public CommonResponse<UserInquiryResponse> updateInquiry(
            @PathVariable Long userId,
            @PathVariable Long inquiryId,
            @RequestBody UserInquiryUpdateRequest request) {
        return CommonResponse.success("문의 내용 수정 성공", null);
    }

    // 특정 유저 문의 내역 삭제
    @DeleteMapping("/{inquiryId}")
    public CommonResponse<Void> deleteInquiry(
            @PathVariable Long userId,
            @PathVariable Long inquiryId) {
        return CommonResponse.success("문의 내역 삭제 성공", null);
    }
}