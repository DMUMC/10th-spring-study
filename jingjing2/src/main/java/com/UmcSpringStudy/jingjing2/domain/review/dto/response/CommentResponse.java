package com.UmcSpringStudy.jingjing2.domain.review.dto.response;

import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {
    private Long commentId;
    private String context;
    private LocalDate date;
    private String writerName;
    private List<CommentResponse> childComments; // 대댓글(자식 노드) 목록
}