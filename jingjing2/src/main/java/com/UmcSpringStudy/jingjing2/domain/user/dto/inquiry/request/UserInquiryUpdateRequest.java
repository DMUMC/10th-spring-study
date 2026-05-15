package com.UmcSpringStudy.jingjing2.domain.user.dto.inquiry.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInquiryUpdateRequest {

    @Schema(description = "수정할 문의 제목", example = "수정) 포인트 적립 누락 관련 추가 문의")
    @NotBlank(message = "문의 제목은 필수 입력 항목입니다.")
    @Size(max = 100, message = "제목은 100자 이내로 작성해주세요.")
    private String title;

    @Schema(description = "수정할 문의 내용", example = "내용을 조금 더 구체적으로 수정합니다. 결제 일시는 오후 2시경입니다.")
    @NotBlank(message = "문의 내용을 입력해주세요.")
    private String context;

    @Schema(description = "수정할 문의 카테고리", example = "SYSTEM_ERROR")
    @NotBlank(message = "문의 카테고리를 선택해주세요.")
    private String category;
}