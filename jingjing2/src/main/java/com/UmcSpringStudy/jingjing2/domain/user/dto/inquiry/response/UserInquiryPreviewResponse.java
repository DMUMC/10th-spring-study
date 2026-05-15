package com.UmcSpringStudy.jingjing2.domain.user.dto.inquiry.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class UserInquiryPreviewResponse {
    private List<UserInquiryResponse> inquiryList;
    private Integer totalPage;
    private Long totalElements;
    private Boolean isFirst;
    private Boolean isLast;
}