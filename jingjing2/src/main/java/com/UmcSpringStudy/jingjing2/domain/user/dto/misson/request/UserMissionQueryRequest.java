package com.UmcSpringStudy.jingjing2.domain.user.dto.misson.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserMissionQueryRequest {

    @Schema(description = "사용자 ID", example = "1")
    @NotNull(message = "사용자 ID는 필수 항목입니다.")
    private Long userId;

    @Schema(description = "페이지 번호 (0부터 시작)", example = "0")
    @Min(value = 0, message = "페이지 번호는 0 이상이어야 합니다.")
    @Builder.Default
    private Integer page = 0;

    @Schema(description = "미션 상태 필터링 (All, PROGRESS, COMPLETE, FAIL)", example = "PROGRESS")
    @Builder.Default
    private String option = "All";
}