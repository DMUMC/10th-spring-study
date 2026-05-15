package com.UmcSpringStudy.jingjing2.domain.user.dto.point.response;

import com.UmcSpringStudy.jingjing2.domain.user.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserPointResponse {
    private Long id;
    private String title;
    private String context;
    private Integer amount;
    private TransactionType type;
}