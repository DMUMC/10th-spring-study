package com.umc.jaengchalttak.domain.inquiry.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record InquiryListResponseDTO (
        Long inquiryId,
        String inquiryTitle,
        LocalDateTime createdAt,
        Boolean isInquiryWait
) { }
