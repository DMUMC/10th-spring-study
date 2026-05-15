package com.UmcSpringStudy.jingjing2.domain.user.converter;

import com.UmcSpringStudy.jingjing2.domain.mission.entity.Mission;
import com.UmcSpringStudy.jingjing2.domain.user.dto.misson.response.UserMissionResponse;
import com.UmcSpringStudy.jingjing2.domain.user.entity.User;
import com.UmcSpringStudy.jingjing2.domain.user.entity.UserMission;
import com.UmcSpringStudy.jingjing2.domain.user.enums.MissionStatus;


public class UserMissionConverter {

    // 1. 도전 시작: User, Mission -> UserMission 엔티티 생성
    public static UserMission toUserMission(User user, Mission mission) {
        return UserMission.builder()
                .user(user)
                .mission(mission)
                .isComplete(MissionStatus.PROGRESS) // 초기 상태: 진행 중
                .progress(0)                      // 초기 진행도: 0
                .build();
    }

    // 2. 엔티티 -> 단일 응답 DTO 변환
    public static UserMissionResponse toUserMissionResponse(UserMission userMission) {
        return UserMissionResponse.builder()
                .id(userMission.getId())
                .missionId(userMission.getMission().getId())
                .missionTitle(userMission.getMission().getName())
                .status(userMission.getIsComplete())
                .progress(userMission.getProgress())
                .build();
    }
}