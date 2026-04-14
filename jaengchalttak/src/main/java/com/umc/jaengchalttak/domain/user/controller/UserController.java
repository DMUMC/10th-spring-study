package com.umc.jaengchalttak.domain.user.controller;

import com.umc.jaengchalttak.domain.user.dto.UserInfoDTO;
import com.umc.jaengchalttak.domain.user.dto.UserAlarmDTO;
import com.umc.jaengchalttak.domain.user.enums.ServiceUseType;
import com.umc.jaengchalttak.global.apiPayload.ApiResponse;
import com.umc.jaengchalttak.global.apiPayload.code.BaseSuccessCode;
import com.umc.jaengchalttak.domain.user.payload.code.UserSuccessCode;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.EnumMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    // ====== 유저 정보 조회  ======
    @GetMapping("/{userId}")
    public ApiResponse<UserInfoDTO> getUserInfo(@PathVariable Long userId) {
        // 임시값 삽입, Service 완성 시 삭제 예정
        Map<ServiceUseType, Boolean> agreement = new EnumMap<>(ServiceUseType.class);
        agreement.put(ServiceUseType.AGE_OVER_14, true);
        agreement.put(ServiceUseType.TERMS_OF_SERVICE, true);
        agreement.put(ServiceUseType.PRIVACY_POLICY, true);
        agreement.put(ServiceUseType.LOCATION_SERVICE, false);
        agreement.put(ServiceUseType.MARKETING, false);

        UserInfoDTO result = new UserInfoDTO(
                agreement,
                "홍길동",
                "MALE",
                new Date(),
                "서울특별시 강남구 테헤란로 123",
                new String[]{"초밥", "치킨", "마라탕"}
        );

        BaseSuccessCode code = UserSuccessCode.USER_CHECK_OK;
        return ApiResponse.onSuccess(code, result);
    }


    // ====== 포인트 정보 조회 ======
    @GetMapping("/point/{userId}")
    public ApiResponse<Integer> getPointInfo(@PathVariable Long userId) {
        // 임시값 삽입, Service 완성 시 삭제 예정
        Integer point = 0;

        BaseSuccessCode code = UserSuccessCode.POINT_CHECK_OK;
        return ApiResponse.onSuccess(code,point);
    }


    // ====== 알림 정보 수정  ======
    @PutMapping("/alarm")
    public ApiResponse<UserAlarmDTO.AlarmResponseDTO> updateAlarm(
            @RequestBody UserAlarmDTO request
    ) {
        // 임시값 삽입, Service 완성 시 삭제 예정
        UserAlarmDTO.AlarmResponseDTO result = new UserAlarmDTO.AlarmResponseDTO(
                true,
                true,
                true
        );
        BaseSuccessCode code = UserSuccessCode.ALARM_OK;
        return ApiResponse.onSuccess(code, result);
    }


    // ====== 닉네임 수정  ======
    @PatchMapping("/name")
    public ApiResponse<UserInfoDTO.UserNameUpdateDTO> changeUserName(
            @RequestBody UserInfoDTO.UserNameUpdateDTO name) {
        // 임시값 삽입, Service 완성 시 삭제 예정
        UserInfoDTO.UserNameUpdateDTO result =
                new UserInfoDTO.UserNameUpdateDTO("홍길동");

        BaseSuccessCode code = UserSuccessCode.CHANGE_NAME_OK;
        return ApiResponse.onSuccess(code,result);
    }


    // ====== 회원 탈퇴  ======
    @DeleteMapping("/{userId}") // 회원 탈퇴
    public ApiResponse<Void> deleteAccount(@PathVariable Long userId) {
        BaseSuccessCode code = UserSuccessCode.DELECT_ACCOUNT_NO_CONTENT;
        return ApiResponse.onSuccess(code,null);
    }

}
