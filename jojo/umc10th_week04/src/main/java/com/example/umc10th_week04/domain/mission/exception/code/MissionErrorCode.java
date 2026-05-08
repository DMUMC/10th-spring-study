package com.example.umc10th_week04.domain.mission.exception.code;

import com.example.umc10th_week04.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MISSION404_1",
            "해당 미션을 찾을 수 없습니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND,
            "STORE404_1",
            "해당 가게를 찾을 수 없습니다."),
    INVALID_MISSION_STATUS(HttpStatus.BAD_REQUEST,
            "MISSION400_1",
            "미션 상태가 올바르지 않습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
