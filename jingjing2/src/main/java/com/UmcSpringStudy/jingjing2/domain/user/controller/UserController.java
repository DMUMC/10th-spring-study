package com.UmcSpringStudy.jingjing2.domain.user.controller;

import com.UmcSpringStudy.jingjing2.domain.user.dto.user.request.*;
import com.UmcSpringStudy.jingjing2.domain.user.dto.user.response.*;
import com.UmcSpringStudy.jingjing2.domain.user.service.UserService;
import com.UmcSpringStudy.jingjing2.global.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    // 1. 로컬 회원가입
    @PostMapping("/join")
    public CommonResponse<Long> join(@RequestBody @Valid UserJoinRequest request) {
        return CommonResponse.success("회원가입 성공", userService.join(request));
    }

    // 2. 가입 후 최초 정보 설정 (온보딩)
    @PatchMapping("/{userId}/initial-info")
    public CommonResponse<UserProfileResponse> setInitialInfo(
            @PathVariable Long userId,
            @RequestBody @Valid UserInitialInfoRequest request) {
        return CommonResponse.success("기초 정보 설정 완료", userService.setInitialInfo(userId, request));
    }

    // 3. 유저 프로필 조회
    @GetMapping("/{userId}")
    public CommonResponse<UserProfileResponse> getUserProfile(@PathVariable Long userId) {
        return CommonResponse.success("유저 프로필 조회 성공", userService.getUserProfile(userId));
    }

    // 4. 유저 정보 수정
    @PatchMapping("/{userId}")
    public CommonResponse<UserProfileResponse> updateUserProfile(
            @PathVariable Long userId,
            @RequestBody @Valid UserProfileUpdateRequest request) {
        return CommonResponse.success("유저 프로필 수정 성공", null);
    }

    // 5. 유저 탈퇴
    @DeleteMapping("/{userId}")
    public CommonResponse<Void> deleteUser(@PathVariable Long userId) {
        return CommonResponse.success("유저 탈퇴 처리 성공", null);
    }
}