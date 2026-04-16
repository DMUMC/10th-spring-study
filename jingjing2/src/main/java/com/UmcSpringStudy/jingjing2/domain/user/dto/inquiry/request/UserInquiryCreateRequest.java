package com.UmcSpringStudy.jingjing2.domain.user.dto.inquiry.request;

import com.UmcSpringStudy.jingjing2.domain.user.enums.InquiryType; // 추가
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInquiryCreateRequest {

    @Schema(description = "문의 제목", example = "포인트 적립 누락")
    @NotBlank(message = "문의 제목은 필수입니다.")
    @Size(max = 30)
    private String title;

    @Schema(description = "문의 내용", example = "결제 후 리워드가 들어오지 않았습니다.")
    @NotBlank(message = "문의 내용을 입력해주세요.")
    private String context;

    @Schema(description = "문의 카테고리", example = "PAYMENT_REWARD")
    @NotNull(message = "문의 카테고리를 선택해주세요.")
    private InquiryType category;
}