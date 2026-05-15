package com.umc.jaengchalttak.domain.mission.payload.code;

import com.umc.jaengchalttak.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    // ====== 200 ======
    MY_MISSION_OK(HttpStatus.OK,
            "MISSION200_1",
            "성공적으로 내 미션을 조회했습니다."),
    MISSION_BY_PROGRESS_OK(HttpStatus.OK,
            "MISSION200_1",
            "성공적으로 내 미션을 조회했습니다."),

    // ====== 201 ======
    START_MISSION_CREATED(HttpStatus.CREATED,
            "MISSION201_1",
            "성공적으로 미션을 시작했습니다."),
    COMPLETE_MISSION_CREATED(HttpStatus.CREATED,
            "MISSION201_1",
            "성공적으로 미션을 완료했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

}
