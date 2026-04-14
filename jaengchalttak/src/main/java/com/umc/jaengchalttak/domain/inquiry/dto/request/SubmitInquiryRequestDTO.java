package com.umc.jaengchalttak.domain.inquiry.dto.request;

public record SubmitInquiryRequestDTO(
        String inquiryTitle,
        String inquiryContent,
        String inquiryType,
        String[] photo
) {

}
