package com.UmcSpringStudy.jingjing2.domain.user.dto.user.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class UserProfileUpdateRequest {
    private String username;
    private String address;
    private String email;
    private String phone;
    private String sex;
    private LocalDate birth;
}