package com.UmcSpringStudy.jingjing2.domain.user.controller;

import com.UmcSpringStudy.jingjing2.domain.user.dto.point.request.UserPointCreateRequest;
import com.UmcSpringStudy.jingjing2.domain.user.dto.point.request.UserPointUpdateRequest;
import com.UmcSpringStudy.jingjing2.domain.user.dto.point.response.UserPointPreviewResponse;
import com.UmcSpringStudy.jingjing2.domain.user.dto.point.response.UserPointResponse;
import com.UmcSpringStudy.jingjing2.global.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/{userId}/points")
public class UserPointController {

    // 특정 유저 포인트 입출금 내역 출력(페이징 포함)
    @GetMapping
    public CommonResponse<UserPointPreviewResponse> getPointHistories(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page) {
        return CommonResponse.success("유저 포인트 내역 조회 성공", null);
    }

    // 특정 유저 포인트 입출금 내역 추가
    @PostMapping
    public CommonResponse<UserPointResponse> addPointHistory(
            @PathVariable Long userId,
            @RequestBody @Valid UserPointCreateRequest request) {
        return CommonResponse.success("유저 포인트 내역 추가 성공", null);
    }

    // 특정 유저 포인트 입출금 내역 수정
    @PatchMapping("/{pointId}")
    public CommonResponse<UserPointResponse> updatePointHistory(
            @PathVariable Long userId,
            @PathVariable Long pointId,
            @RequestBody @Valid UserPointUpdateRequest request) {
        return CommonResponse.success("유저 포인트 내역 수정 성공", null);
    }

    // 특정 유저 포인트 입출금 내역 삭제
    @DeleteMapping("/{pointId}")
    public CommonResponse<Void> deletePointHistory(
            @PathVariable Long userId,
            @PathVariable Long pointId) {
        return CommonResponse.success("유저 포인트 내역 삭제 성공", null);
    }
}