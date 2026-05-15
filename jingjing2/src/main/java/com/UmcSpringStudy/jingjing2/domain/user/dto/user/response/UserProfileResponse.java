package com.UmcSpringStudy.jingjing2.domain.user.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class UserProfileResponse {
    private Long userId;
    private String username;
    private String email;
    private String phone;
    private Integer point;
    private List<String> interests; // 관심사 키워드 목록
}