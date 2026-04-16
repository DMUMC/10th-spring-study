package com.UmcSpringStudy.jingjing2.domain.review.dto.response;

import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewPreviewResponse {
    private Long reviewId;
    private String title;
    private String context;
    private Double rate;
    private Integer popularity;
    private LocalDate createdDate;
    private Long userId;
    private Long storeId;
}