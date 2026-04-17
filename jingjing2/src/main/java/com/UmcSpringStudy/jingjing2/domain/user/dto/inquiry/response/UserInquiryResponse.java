package com.UmcSpringStudy.jingjing2.domain.user.dto.inquiry.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class UserInquiryResponse {
    private Long id;
    private String title;
    private String context;
    private String category;
    private String response; // 답변 내용
    private List<String> fileUrls; // 첨부파일 URL 목록
}