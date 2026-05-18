package com.UmcSpringStudy.jingjing2.domain.review.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyReviewTargetRequest {

    @Schema(description = "사용자 ID", example = "1")
    @NotNull(message = "사용자 ID는 필수입니다.")
    private Long userId;

    @Schema(description = "마지막으로 조회된 리뷰 ID (커서)", example = "null")
    private Long cursor;

    @Schema(description = "별점순 정렬 시 마지막 별점 커서 (0.5 ~ 5.0)", example = "null")
    private Double cursorRate;

    @Schema(description = "정렬 방식 (latest: 최신순, rate: 별점순)", example = "latest")
    @Builder.Default
    private String sort = "latest";

    @Schema(description = "한 번에 조회할 페이지 크기 (1~50)", example = "10")
    @Min(value = 1, message = "사이즈는 최소 1 이상이어야 합니다.")
    @Max(value = 50, message = "사이즈는 최대 50 이하여야 합니다.")
    @Builder.Default
    private Integer size = 10;
}