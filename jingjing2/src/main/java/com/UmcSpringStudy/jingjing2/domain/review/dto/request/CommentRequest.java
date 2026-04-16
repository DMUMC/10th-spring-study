package com.UmcSpringStudy.jingjing2.domain.review.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {

    @Schema(description = "댓글 내용", example = "음식이 정말 맛있고 서비스가 친절해요!")
    @NotBlank(message = "댓글 내용을 입력해주세요.")
    private String context;

    @Schema(description = "작성자 ID", example = "1")
    @NotNull(message = "작성자 ID는 필수입니다.")
    private Long userId;

    @Schema(description = "부모 댓글 ID (대댓글인 경우 입력, 일반 댓글은 null)", example = "null")
    private Long parentId; // 대댓글 여부 결정 필드 (null 허용)
}