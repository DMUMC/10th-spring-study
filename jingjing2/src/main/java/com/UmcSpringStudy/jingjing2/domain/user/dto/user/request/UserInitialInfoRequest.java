package com.UmcSpringStudy.jingjing2.domain.user.dto.user.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInitialInfoRequest {

    @Schema(description = "닉네임", example = "징징이")
    @NotBlank(message = "닉네임은 필수입니다.")
    @Size(min = 2, max = 20, message = "닉네임은 2자 이상 20자 이내여야 합니다.")
    private String username;

    @Schema(description = "성별 (M: 남성, F: 여성)", example = "M", allowableValues = {"M", "F"})
    @NotBlank(message = "성별을 선택해주세요.")
    @Pattern(regexp = "^[MF]$", message = "성별은 M 또는 F여야 합니다.")
    private String sex;

    @Schema(description = "생년월일", example = "2000-01-01")
    @NotNull(message = "생년월일을 입력해주세요.")
    @Past(message = "유효하지 않은 생년월일입니다.")
    private LocalDate birth;

    @Schema(description = "전화번호", example = "010-1234-5678")
    @NotBlank(message = "전화번호는 필수입니다.")
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "형식에 맞춰 입력해주세요. (010-0000-0000)")
    private String phone;

    @Schema(description = "주소", example = "서울특별시 구로구 경인로 445")
    private String address; // 선택 사항
}