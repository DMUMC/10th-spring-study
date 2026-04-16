package com.UmcSpringStudy.jingjing2.domain.user.dto.user.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileUpdateRequest {

    @Schema(description = "수정할 이름", example = "징징2")
    @NotBlank(message = "사용자 이름은 필수입니다.")
    @Size(max = 20, message = "이름은 20자 이내여야 합니다.")
    private String username;

    @Schema(description = "수정할 주소", example = "인천광역시 미추홀구")
    private String address;

    @Schema(description = "수정할 이메일", example = "updated@gmail.com")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @Schema(description = "수정할 전화번호", example = "010-9876-5432")
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "전화번호 형식이 올바르지 않습니다. (010-0000-0000)")
    private String phone;

    @Schema(description = "수정할 성별", example = "M")
    @Pattern(regexp = "^[MF]$", message = "성별은 M 또는 F여야 합니다.")
    private String sex;

    @Schema(description = "수정할 생년월일", example = "2000-05-05")
    @Past(message = "생년월일은 미래 날짜일 수 없습니다.")
    private LocalDate birth;
}