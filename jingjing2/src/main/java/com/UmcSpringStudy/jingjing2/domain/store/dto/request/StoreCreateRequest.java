package com.UmcSpringStudy.jingjing2.domain.store.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreCreateRequest {

    @Schema(description = "가게 이름", example = "요미우돈교자")
    @NotBlank(message = "가게 이름은 필수 입력 항목입니다.")
    @Size(max = 50, message = "가게 이름은 50자 이내여야 합니다.")
    private String name;

    @Schema(description = "카테고리", example = "일식")
    @NotBlank(message = "카테고리를 지정해주세요.")
    private String category;

    @Schema(description = "구역id", example = "1234")
    @NotNull(message = "가게의 구역 id를 입력 해 주세요.")
    private Long regionId;


}