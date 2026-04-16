package com.UmcSpringStudy.jingjing2.global.exception.errorcodes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    MISSION_EXPIRED(HttpStatus.BAD_REQUEST, "MISSION_4000", "기간이 만료된 미션입니다."),
    MISSION_NOT_COMPLETED(HttpStatus.BAD_REQUEST, "MISSION_4001", "미션 달성 조건을 충족하지 못했습니다."),

    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION_4040", "해당 미션을 찾을 수 없습니다."),

    ALREADY_PARTICIPATED(HttpStatus.CONFLICT, "MISSION_4090", "이미 참여 중인 미션입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}