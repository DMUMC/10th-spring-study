package com.UmcSpringStudy.jingjing2.domain.review.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewCreateRequest {
    private String title;
    private String context;
    private Double rate;
    private Long userId;   // 타 도메인 ID 참조
    private Long storeId;  // 타 도메인 ID 참조
}