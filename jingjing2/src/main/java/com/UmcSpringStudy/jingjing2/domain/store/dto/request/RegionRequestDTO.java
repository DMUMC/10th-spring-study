package com.UmcSpringStudy.jingjing2.domain.store.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RegionRequestDTO {
    @Schema(description = "구역 이름", example = "고척동")
    @NotBlank(message = "구역 이름은 필수 입력 항목입니다.")
    private String name;
}