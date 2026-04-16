package com.UmcSpringStudy.jingjing2.domain.user.dto.point.request;

import com.UmcSpringStudy.jingjing2.domain.user.enums.TransactionType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserPointCreateRequest {
    private String title;
    private String context;
    private Integer amount;
    private TransactionType type;
}