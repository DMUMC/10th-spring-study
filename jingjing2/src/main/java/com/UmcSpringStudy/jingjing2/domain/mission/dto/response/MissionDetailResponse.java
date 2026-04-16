package com.UmcSpringStudy.jingjing2.domain.mission.dto.response;

import lombok.*;
import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MissionDetailResponse {
    private Long missionId;
    private String name;
    private String context;
    private Double rate;
    private Integer taskCount;
    private String reward;
    private LocalDate expDate;
    private Long storeId; // ID 참조 방식 유지
}