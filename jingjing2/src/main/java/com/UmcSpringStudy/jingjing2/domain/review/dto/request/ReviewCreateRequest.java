package com.UmcSpringStudy.jingjing2.domain.review.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewCreateRequest {

    @Schema(description = "리뷰 제목", example = "최고의 돈까스 맛집입니다!")
    @NotBlank(message = "리뷰 제목은 필수 입력 항목입니다.")
    @Size(max = 100, message = "제목은 100자 이내로 작성해주세요.")
    private String title;

    @Schema(description = "리뷰 상세 내용", example = "고기가 두툼하고 튀김옷이 아주 바삭해요. 재방문 의사 200%입니다.")
    @NotBlank(message = "리뷰 내용을 입력해주세요.")
    private String context;

    @Schema(description = "평점 (0.5 ~ 5.0)", example = "4.5")
    @NotNull(message = "평점을 입력해주세요.")
    @DecimalMin(value = "0.5", message = "평점은 최소 0.5점 이상이어야 합니다.")
    @DecimalMax(value = "5.0", message = "평점은 최대 5.0점 이하여야 합니다.")
    private Double rate;

    @Schema(description = "작성자 ID", example = "1")
    @NotNull(message = "작성자 ID는 필수입니다.")
    private Long userId;

    @Schema(description = "가게 ID", example = "1")
    @NotNull(message = "가게 ID는 필수입니다.")
    private Long storeId;
}