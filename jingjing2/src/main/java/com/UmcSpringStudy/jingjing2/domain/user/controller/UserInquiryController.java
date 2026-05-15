package com.UmcSpringStudy.jingjing2.domain.user.controller;


import com.UmcSpringStudy.jingjing2.domain.user.dto.inquiry.request.UserInquiryCreateRequest;
import com.UmcSpringStudy.jingjing2.domain.user.dto.inquiry.request.UserInquiryUpdateRequest;
import com.UmcSpringStudy.jingjing2.domain.user.dto.inquiry.response.*;
import com.UmcSpringStudy.jingjing2.global.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/{userId}/inquiries")
public class UserInquiryController {

    // 특정 유저 문의 작성
    // json 과 파일이 섞여 있음에 따라 그냥 요청 시 오류 뜨고,
    // Content-Type 을 통해서 application/json, image/jpeg 명시해야 함
    @PostMapping(consumes = "multipart/form-data")
    public CommonResponse<UserInquiryResponse> createInquiry(
            @PathVariable Long userId,
            @RequestPart(value = "request") @Valid UserInquiryCreateRequest request,
            @RequestPart(value = "files", required = false) List<MultipartFile> files) {

        return CommonResponse.success("문의 사항이 등록되었습니다.", null);
    }
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
            @RequestBody @Valid UserInquiryUpdateRequest request) {
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