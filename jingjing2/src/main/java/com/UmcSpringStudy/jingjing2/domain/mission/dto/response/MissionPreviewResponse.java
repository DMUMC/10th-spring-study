package com.UmcSpringStudy.jingjing2.domain.mission.dto.response;

import lombok.*;
import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MissionPreviewResponse {
    private Long missionId;
    private String name;
    private String reward;
    private LocalDate expDate;
    private String storeName;
    private String storeCategory;
}