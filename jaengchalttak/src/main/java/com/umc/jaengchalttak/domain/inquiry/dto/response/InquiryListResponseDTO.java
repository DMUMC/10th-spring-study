package com.umc.jaengchalttak.domain.inquiry.dto.response;

import java.time.LocalDateTime;

public record InquiryListResponseDTO (
        Long inquiryId,
        String inquiryTitle,
        LocalDateTime createdAt,
        Boolean isInquiryWait
) { }
