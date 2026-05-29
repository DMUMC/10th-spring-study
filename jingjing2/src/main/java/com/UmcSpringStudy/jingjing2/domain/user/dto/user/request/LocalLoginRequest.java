package com.UmcSpringStudy.jingjing2.domain.user.dto.user.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LocalLoginRequest {

    @Schema(description = "이메일", example = "user@example.com")
    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @Schema(description = "비밀번호", example = "Password123!")
    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    private String password;
}