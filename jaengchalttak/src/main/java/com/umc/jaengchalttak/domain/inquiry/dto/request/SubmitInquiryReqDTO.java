package com.umc.jaengchalttak.domain.inquiry.dto.request;

public record SubmitInquiryReqDTO(
        String inquiryTitle,
        String inquiryContent,
        String inquiryType,
        String[] photo
) {

}
