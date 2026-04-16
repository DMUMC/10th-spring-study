package com.UmcSpringStudy.jingjing2.domain.user.dto.inquiry.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserInquiryUpdateRequest {
    private String title;
    private String context;
    private String category;
}