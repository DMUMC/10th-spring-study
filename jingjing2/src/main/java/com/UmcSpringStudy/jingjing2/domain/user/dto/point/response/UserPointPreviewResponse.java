package com.UmcSpringStudy.jingjing2.domain.user.dto.point.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class UserPointPreviewResponse {
    private List<UserPointResponse> pointList;
    private Integer totalPage;
    private Long totalElements;
    private Boolean isFirst;
    private Boolean isLast;
}