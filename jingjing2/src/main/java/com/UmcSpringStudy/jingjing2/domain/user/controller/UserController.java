package com.UmcSpringStudy.jingjing2.domain.user.controller;

import com.UmcSpringStudy.jingjing2.domain.user.dto.user.request.UserProfileUpdateRequest;
import com.UmcSpringStudy.jingjing2.domain.user.dto.user.request.UserSettingsUpdateRequest;
import com.UmcSpringStudy.jingjing2.domain.user.dto.user.response.UserProfileResponse;
import com.UmcSpringStudy.jingjing2.domain.user.dto.user.response.UserSettingsResponse;
import com.UmcSpringStudy.jingjing2.global.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    // 특정 유저 정보 출력
    @GetMapping("/{userId}")
    public CommonResponse<UserProfileResponse> getUserProfile(@PathVariable Long userId) {
        return CommonResponse.success("유저 프로필 조회 성공", null);
    }

    // 특정 유저 정보 수정
    @PatchMapping("/{userId}")
    public CommonResponse<UserProfileResponse> updateUserProfile(
            @PathVariable Long userId,
            @RequestBody UserProfileUpdateRequest request) {
        return CommonResponse.success("유저 프로필 수정 성공", null);
    }

    // 특정 유저 탈퇴
    @DeleteMapping("/{userId}")
    public CommonResponse<Void> deleteUser(@PathVariable Long userId) {
        return CommonResponse.success("유저 탈퇴 처리 성공", null);
    }

    // 특정 유저 약관, 개인설정 출력 (Setting + Autorication)
    @GetMapping("/{userId}/settings")
    public CommonResponse<UserSettingsResponse> getUserSettings(@PathVariable Long userId) {
        return CommonResponse.success("유저 설정 조회 성공", null);
    }

    // 특정 유저 약관, 개인설정 업데이트
    @PutMapping("/{userId}/settings")
    public CommonResponse<UserSettingsResponse> updateUserSettings(
            @PathVariable Long userId,
            @RequestBody UserSettingsUpdateRequest request) {
        return CommonResponse.success("유저 설정 업데이트 성공", null);
    }


    // 유저 관심사 설정/추가
    @PostMapping("/{userId}/interests")
    public CommonResponse<Void> addUserInterests(
            @PathVariable Long userId,
            @RequestBody List<Long> request) {
        return CommonResponse.success("유저 관심사 설정 성공", null);
    }

    // 유저 특정 관심사 삭제
    @DeleteMapping("/{userId}/interests/{interestId}")
    public CommonResponse<Void> deleteUserInterest(
            @PathVariable Long userId,
            @PathVariable Long interestId) {
        return CommonResponse.success("유저 관심사 삭제 성공", null);
    }
}