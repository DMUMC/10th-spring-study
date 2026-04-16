package com.UmcSpringStudy.jingjing2.domain.review.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewUpdateRequest {

    @Schema(description = "수정할 제목", example = "수정) 고기가 조금 얇아진 것 같지만 여전히 맛있네요.")
    @Size(max = 100, message = "제목은 100자 이내로 작성해주세요.")
    private String title;

    @Schema(description = "수정할 내용", example = "지난번보다 조금 아쉬웠지만 그래도 전반적으로 만족합니다.")
    private String context;

    @Schema(description = "수정할 평점 (0.5 ~ 5.0)", example = "3.5")
    @DecimalMin(value = "0.5")
    @DecimalMax(value = "5.0")
    private Double rate;
}