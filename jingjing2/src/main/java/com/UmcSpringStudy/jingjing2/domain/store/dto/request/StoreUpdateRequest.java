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
public class StoreUpdateRequest {

    @Schema(description = "수정할 가게 이름", example = "요미우돈교자 강남점")
    @Size(max = 50, message = "가게 이름은 50자 이내여야 합니다.")
    private String name;

    @Schema(description = "수정할 카테고리", example = "일식/우동")
    private String category;

    @Schema(description = "수정할 위도", example = "37.5000")
    @DecimalMin(value = "-90.0")
    @DecimalMax(value = "90.0")
    private BigDecimal lat;

    @Schema(description = "수정할 경도", example = "127.0000")
    @DecimalMin(value = "-180.0")
    @DecimalMax(value = "180.0")
    private BigDecimal lng;

    @Schema(description = "수정할 평점", example = "4.8")
    @DecimalMin(value = "0.0", message = "평점은 0.0 이상이어야 합니다.")
    @DecimalMax(value = "5.0", message = "평점은 5.0 이하여야 합니다.")
    private Double rate;
}