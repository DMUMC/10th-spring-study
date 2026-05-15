package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.enums.Status;
import java.util.List;
import lombok.Builder;

import java.time.LocalDateTime;

public class MissionResDTO {
    @Builder
    public record GetInfo(
            Long missionId,
            String title,
            Integer point,
            String storeName,
            String information,
            LocalDateTime deadline,
            Status status
    ) {}


}
