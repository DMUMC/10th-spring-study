package com.UmcSpringStudy.jingjing2.domain.store.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

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

    @Schema(description = "위도(Latitude)", example = "37.5665")
    @NotNull(message = "위도(Latitude) 정보가 필요합니다.")
    @DecimalMin(value = "-90.0", message = "위도는 -90.0에서 90.0 사이여야 합니다.")
    @DecimalMax(value = "90.0", message = "위도는 -90.0에서 90.0 사이여야 합니다.")
    private BigDecimal lat;

    @Schema(description = "경도(Longitude)", example = "126.9780")
    @NotNull(message = "경도(Longitude) 정보가 필요합니다.")
    @DecimalMin(value = "-180.0", message = "경도는 -180.0에서 180.0 사이여야 합니다.")
    @DecimalMax(value = "180.0", message = "경도는 -180.0에서 180.0 사이여야 합니다.")
    private BigDecimal lng;
}