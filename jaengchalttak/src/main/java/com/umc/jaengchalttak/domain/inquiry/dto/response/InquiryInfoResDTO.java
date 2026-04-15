package com.umc.jaengchalttak.domain.inquiry.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record InquiryInfoResDTO(
        String inquiryTitle,
        String inquiryContent,
        String inquiryType,
        String [] reviewSavePath,
        LocalDateTime createdAt,
        Boolean isInquiryWait
) { }
