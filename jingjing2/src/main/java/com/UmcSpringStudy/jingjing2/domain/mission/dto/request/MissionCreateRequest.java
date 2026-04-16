package com.UmcSpringStudy.jingjing2.domain.mission.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class MissionCreateRequest {

    @Schema(description = "미션 이름", example = "당근 3회 구매하기")
    @NotBlank(message = "미션 이름은 필수 입력 항목입니다.")
    @Size(max = 50, message = "미션 이름은 50자 이내로 입력해주세요.")
    private String name;

    @Schema(description = "미션 내용", example = "매장에서 신선한 당근을 3번 구매하세요.")
    @NotBlank(message = "미션 내용은 필수 입력 항목입니다.")
    private String context;

    @Schema(description = "미션 평점", example = "4.5")
    @NotNull(message = "미션 점수(평점)를 입력해주세요.")
    @DecimalMin(value = "0.0") @DecimalMax(value = "5.0")
    private Double rate;

    @Schema(description = "목표 횟수", example = "3")
    @NotNull(message = "목표 횟수를 입력해주세요.")
    @Min(value = 1) @Max(value = 5)
    private Integer taskCount;

    @Schema(description = "리워드", example = "500포인트")
    @NotBlank(message = "리워드 정보를 입력해주세요.")
    private String reward;

    @Schema(description = "만료일", example = "2026-12-31")
    @NotNull(message = "만료일을 선택해주세요.")
    @FutureOrPresent
    private LocalDate expDate;

    @Schema(description = "가게 ID", example = "1")
    @NotNull(message = "가게 ID는 필수입니다.")
    private Long storeId;
}