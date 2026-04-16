package com.UmcSpringStudy.jingjing2.domain.review.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {
    private String context;
    private Long userId;    // 작성자 ID
    private Long parentId;  // 대댓글일 경우 부모 댓글의 ID (일반 댓글은 null)
}