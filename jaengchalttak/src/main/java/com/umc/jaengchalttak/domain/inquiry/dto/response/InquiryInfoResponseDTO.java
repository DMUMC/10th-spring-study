package com.umc.jaengchalttak.domain.inquiry.dto.response;

import java.time.LocalDateTime;

public record InquiryInfoResponseDTO(
        String inquiryTitle,
        String inquiryContent,
        String inquiryType,
        String [] reviewSavePath,
        LocalDateTime createdAt,
        Boolean isInquiryWait
) { }
