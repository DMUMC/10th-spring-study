package com.UmcSpringStudy.jingjing2.domain.mission.dto.request;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MissionCreateRequest {
    private String name;
    private String context;
    private Double rate;
    private Integer taskCount;
    private String reward;
    private LocalDate expDate;
    private Long storeId; // ID 참조 방식
}