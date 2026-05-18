package com.umc.jaengchalttak.domain.inquiry.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

public record SubmitInquiryReqDTO(
        @NotBlank(message = "문의 제목은 필수입니다.")
        @Size(max = 50, message = "문의 제목은 50자 이내여야 합니다.")
        String inquiryTitle,

        @NotBlank(message = "문의 내용은 필수입니다.")
        @Size(max = 500, message = "문의 내용은 500자 이내여야 합니다.")
        String inquiryContent,

        @NotBlank(message = "문의 타입은 필수입니다.")
        @Size(max = 20, message = "문의 타입은 20자 이내여야 합니다.")
        String inquiryType,

        List<String> photo
) {

}
