package com.UmcSpringStudy.jingjing2.domain.review.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewUpdateRequest {
    private String title;
    private String context;
    private Double rate;
}