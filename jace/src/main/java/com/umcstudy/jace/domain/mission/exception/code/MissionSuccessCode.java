package com.umcstudy.jace.domain.mission.exception.code;

import com.umcstudy.jace.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {
    HomeOK(HttpStatus.OK, "MISSION200_1", "홈 화면 데이터를 불러오는데에 성공했습니다."),
    MyMissionOK(HttpStatus.OK, "MISSION200_2", "미션 목록을 불러오는데에 성공했습니다."),;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
