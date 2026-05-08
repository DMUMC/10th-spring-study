package com.umc.jaengchalttak.domain.inquiry.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record InquiryInfoResDTO(
        String inquiryTitle,
        String inquiryContent,
        String inquiryType,
        List<String> reviewSavePath,
        LocalDateTime createdAt,
        Boolean isInquiryWait
) { }
