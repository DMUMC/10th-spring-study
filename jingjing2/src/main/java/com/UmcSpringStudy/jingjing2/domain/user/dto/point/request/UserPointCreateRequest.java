package com.UmcSpringStudy.jingjing2.domain.user.dto.point.request;

import com.UmcSpringStudy.jingjing2.domain.user.enums.TransactionType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPointCreateRequest {

    @Schema(description = "내역 제목", example = "미션 완료 보상 적립")
    @NotBlank(message = "내역 제목을 입력해주세요.")
    @Size(max = 50, message = "제목은 50자 이내로 입력해주세요.")
    private String title;

    @Schema(description = "상세 내용", example = "당근 3회 구매 미션 달성으로 인한 포인트 지급")
    private String context;

    @Schema(description = "거래 금액", example = "500")
    @NotNull(message = "금액은 필수 입력 사항입니다.")
    @Positive(message = "금액은 0보다 커야 합니다.")
    private Integer amount;

    @Schema(description = "거래 타입", example = "PLUS", allowableValues = {"PLUS", "MINUS"})
    @NotNull(message = "거래 타입(PLUS/MINUS)을 선택해주세요.")
    private TransactionType type;
}