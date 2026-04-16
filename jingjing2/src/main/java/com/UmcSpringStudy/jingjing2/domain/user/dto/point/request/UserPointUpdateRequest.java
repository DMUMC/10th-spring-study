package com.UmcSpringStudy.jingjing2.domain.user.dto.point.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserPointUpdateRequest {
    private String title;
    private String context;
    private Integer amount;
}