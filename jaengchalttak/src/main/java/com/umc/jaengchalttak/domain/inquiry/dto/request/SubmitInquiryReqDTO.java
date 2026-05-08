package com.umc.jaengchalttak.domain.inquiry.dto.request;

import java.util.List;

public record SubmitInquiryReqDTO(
        String inquiryTitle,
        String inquiryContent,
        String inquiryType,
        List<String> photo
) {

}
