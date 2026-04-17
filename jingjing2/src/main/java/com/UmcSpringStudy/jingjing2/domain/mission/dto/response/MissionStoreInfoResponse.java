package com.UmcSpringStudy.jingjing2.domain.mission.dto.response;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MissionStoreInfoResponse {
    private Long missionId;
    private Long storeId;
    private String storeName;
    private String category;
}