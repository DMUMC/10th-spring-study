package com.UmcSpringStudy.jingjing2.domain.user.dto.point.request;

import com.UmcSpringStudy.jingjing2.domain.user.enums.TransactionType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPointUpdateRequest {

    @Schema(description = "수정할 내역 제목", example = "포인트 사용 내역 수정")
    @Size(max = 50, message = "제목은 50자 이내로 입력해주세요.")
    private String title;

    @Schema(description = "수정할 상세 내용", example = "기존 내역 오기로 인한 수정")
    private String context;

    @Schema(description = "수정할 금액", example = "1000")
    @Positive(message = "금액은 0보다 커야 합니다.")
    private Integer amount;

    @Schema(description = "거래 타입", example = "PLUS", allowableValues = {"PLUS", "MINUS"})
    @NotNull(message = "거래 타입(PLUS/MINUS)을 선택해주세요.")
    private TransactionType type;
}