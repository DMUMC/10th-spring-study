package com.UmcSpringStudy.jingjing2.domain.mission.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class MissionUpdateRequest {

    @Schema(description = "수정할 이름", example = "양파 3회 구매하기")
    @Size(max = 50)
    private String name;

    @Schema(description = "수정할 내용", example = "양파를 3번 구매하면 리워드 증정")
    private String context;

    @Schema(description = "수정할 평점", example = "4.0")
    @DecimalMin(value = "0.0") @DecimalMax(value = "5.0")
    private Double rate;

    @Schema(description = "수정할 목표 횟수", example = "3")
    @Min(value = 1)
    @Max(value = 5)
    private Integer taskCount;

    @Schema(description = "수정할 리워드", example = "1000포인트")
    private String reward;

    @Schema(description = "수정할 만료일", example = "2027-01-01")
    @FutureOrPresent
    private LocalDate expDate;
}