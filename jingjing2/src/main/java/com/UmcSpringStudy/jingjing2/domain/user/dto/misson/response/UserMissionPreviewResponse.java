package com.UmcSpringStudy.jingjing2.domain.user.dto.misson.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class UserMissionPreviewResponse {
    private List<UserMissionResponse> missionList;
    private Integer totalPage;
    private Long totalElements;
    private Boolean isFirst;
    private Boolean isLast;
}