package com.UmcSpringStudy.jingjing2.domain.user.dto.misson.response;

import com.UmcSpringStudy.jingjing2.domain.user.enums.MissionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserMissionResponse {
    private Long id;              // UserMission 엔티티 PK
    private Long missionId;       // 원본 미션 ID
    private String missionTitle;  // 미션 제목
    private MissionStatus status; // 현재 상태
    private Integer progress;     // 진행도
}