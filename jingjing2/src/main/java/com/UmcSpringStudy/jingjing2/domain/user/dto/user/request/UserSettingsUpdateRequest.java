package com.UmcSpringStudy.jingjing2.domain.user.dto.user.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSettingsUpdateRequest {

    @Schema(description = "이벤트 알림 설정 여부", example = "true", allowableValues = {"true", "false"})
    @NotNull(message = "이벤트 알림 설정 값은 필수입니다.")
    private Boolean eventNotice;

    @Schema(description = "리뷰 알림 설정 여부", example = "true", allowableValues = {"true", "false"})
    @NotNull(message = "리뷰 알림 설정 값은 필수입니다.")
    private Boolean reviewNotice;

    @Schema(description = "문의 답장 알림 설정 여부", example = "false", allowableValues = {"true", "false"})
    @NotNull(message = "문의 알림 설정 값은 필수입니다.")
    private Boolean iqNotice;

    @Schema(description = "광고 동의 여부", example = "true", allowableValues = {"true", "false"})
    @NotNull(message = "광고 동의 여부는 필수입니다.")
    private Boolean adAllow;

    @Schema(description = "위치 정보 동의 여부", example = "false", allowableValues = {"true", "false"})
    @NotNull(message = "위치 정보 동의 여부는 필수입니다.")
    private Boolean locAllow;
}