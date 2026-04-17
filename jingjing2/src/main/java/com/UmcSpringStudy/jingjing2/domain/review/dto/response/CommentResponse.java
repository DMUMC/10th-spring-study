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
    private String writerName; // userId로 조회한 결과
    private List<CommentResponse> childComments; // 대댓글(자식 노드) 목록
}