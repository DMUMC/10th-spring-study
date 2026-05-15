package com.UmcSpringStudy.jingjing2.domain.review.dto.response;

import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponse {
    private Long reviewId;
    private String title;
    private String context;
    private Double rate;
    private Integer popularity;
    private LocalDate createdDate;
    private Long userId;
    private Long storeId;

    private List<String> imageUrls;        // 첨부이미지 URL 목록
    private List<CommentResponse> comments; // 댓글 및 대댓글 트리
}