package com.UmcSpringStudy.jingjing2.domain.user.dto.misson.request;

import com.UmcSpringStudy.jingjing2.domain.user.enums.MissionStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserMissionUpdateRequest {
    private MissionStatus status; // PROGRESS, COMPLETE, FAIL
    private Integer progress;     // 진행도 수치
}