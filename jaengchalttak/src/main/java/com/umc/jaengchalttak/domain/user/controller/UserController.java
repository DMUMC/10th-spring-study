package com.umc.jaengchalttak.domain.user.controller;

import com.umc.jaengchalttak.domain.user.dto.UserInfoDTO;
import com.umc.jaengchalttak.domain.user.dto.UserAlarmDTO;
import com.umc.jaengchalttak.domain.user.service.UserService;
import com.umc.jaengchalttak.global.apiPayload.ApiResponse;
import com.umc.jaengchalttak.global.apiPayload.code.BaseSuccessCode;
import com.umc.jaengchalttak.domain.user.payload.code.UserSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "마이페이지 유저 정보 조회", description = "유저의 기본 인적 사항 및 약관 동의 현황을 조회합니다.")
    @GetMapping("/{userId}")
    public ApiResponse<UserInfoDTO> getUserInfo(@PathVariable Long userId) {
        UserInfoDTO result = userService.findUserInfo(userId);

        BaseSuccessCode code = UserSuccessCode.USER_CHECK_OK;
        return ApiResponse.onSuccess(code, result);
    }


    @Operation(summary = "유저 보유 포인트 조회", description = "현재 유저가 사용 가능한 가용 포인트를 조회합니다.")
    @GetMapping("/point/{userId}")
    public ApiResponse<Integer> getPointInfo(@PathVariable Long userId) {
        // 임시값 삽입, Service 완성 시 삭제 예정
        Integer point = 0;

        BaseSuccessCode code = UserSuccessCode.POINT_CHECK_OK;
        return ApiResponse.onSuccess(code,point);
    }


    @Operation(summary = "알림 설정 업데이트", description = "개별 알림 수신 여부를 수정합니다.")
    @PutMapping("/alarm")
    public ApiResponse<UserAlarmDTO.alarmResDTO> updateAlarm(
            @RequestBody UserAlarmDTO request
    ) {
        // 임시값 삽입, Service 완성 시 삭제 예정
        UserAlarmDTO.alarmResDTO result = UserAlarmDTO.alarmResDTO.builder()
                .newEvent(true)
                .review_answer(true)
                .inquiry(true)
                .build();

        BaseSuccessCode code = UserSuccessCode.ALARM_OK;
        return ApiResponse.onSuccess(code, result);
    }


    @Operation(summary = "유저 닉네임 변경", description = "유저의 서비스 내 활동명을 변경합니다.")
    @PatchMapping("/name")
    public ApiResponse<UserInfoDTO.userNameUpdateDTO> changeUserName(
            @RequestBody UserInfoDTO.userNameUpdateDTO name) {
        // 임시값 삽입, Service 완성 시 삭제 예정
        UserInfoDTO.userNameUpdateDTO result =
                UserInfoDTO.userNameUpdateDTO.builder()
                        .name("홍길동")
                        .build();

        BaseSuccessCode code = UserSuccessCode.CHANGE_NAME_OK;
        return ApiResponse.onSuccess(code, result);
    }


    @Operation(summary = "회원 탈퇴", description = "유저 계정을 삭제 처리합니다.")
    @DeleteMapping("/{userId}") // 회원 탈퇴
    public ApiResponse<Void> deleteAccount(@PathVariable Long userId) {
        BaseSuccessCode code = UserSuccessCode.DELETE_ACCOUNT_NO_CONTENT;
        return ApiResponse.onSuccess(code,null);
    }

}
