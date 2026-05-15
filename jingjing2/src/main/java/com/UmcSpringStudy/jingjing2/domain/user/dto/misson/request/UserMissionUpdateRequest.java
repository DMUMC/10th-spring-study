package com.UmcSpringStudy.jingjing2.domain.user.dto.misson.request;

import com.UmcSpringStudy.jingjing2.domain.user.enums.MissionStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserMissionUpdateRequest {

    @Schema(description = "변경할 미션 상태", example = "PROGRESS", allowableValues = {"PROGRESS", "COMPLETE", "FAIL"})
    @NotNull(message = "변경할 미션 상태를 입력해주세요.")
    private MissionStatus status; // PROGRESS, COMPLETE, FAIL

    @Schema(description = "현재 미션 진행도 (누적 횟수 등)", example = "3")
    @Min(value = 0, message = "진행도는 0 미만이 될 수 없습니다.")
    private Integer progress;     // 진행도 수치
}