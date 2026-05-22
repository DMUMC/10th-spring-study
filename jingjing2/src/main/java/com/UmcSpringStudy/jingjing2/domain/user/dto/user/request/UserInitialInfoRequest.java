package com.UmcSpringStudy.jingjing2.domain.user.dto.user.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInitialInfoRequest {

    // ... (기존 필드: username, sex, birth, phone, address, interestIds 유지) ...

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
    private String address;

    @Schema(description = "선호 음식(관심사) ID 목록", example = "[1, 3, 5]")
    @NotNull(message = "선호 음식 종류를 선택해주세요.")
    @Size(min = 1, message = "최소 1개 이상의 선호 음식을 선택해야 합니다.")
    private List<Long> interestIds;

    // --- 👇 추가된 약관 동의 영역 👇 ---

    @Schema(description = "만 14세 이상 (필수)", example = "true")
    @NotNull(message = "만 14세 이상 확인은 필수입니다.")
    @AssertTrue(message = "만 14세 이상이어야 서비스를 이용할 수 있습니다.")
    private Boolean overFourteen;

    @Schema(description = "서비스 이용약관 동의 (필수)", example = "true")
    @NotNull(message = "서비스 이용약관 동의는 필수입니다.")
    @AssertTrue(message = "필수 서비스 이용약관에 동의해야 합니다.")
    private Boolean termsOfService;

    @Schema(description = "개인 정보 처리 방침 동의 (필수)", example = "true")
    @NotNull(message = "개인 정보 처리 방침 동의는 필수입니다.")
    @AssertTrue(message = "필수 개인 정보 처리 방침에 동의해야 합니다.")
    private Boolean privacyPolicy;

    @Schema(description = "위치정보 제공 동의 (선택)", example = "false")
    @NotNull(message = "위치정보 제공 동의 여부를 선택해주세요.")
    private Boolean locAllow;

    @Schema(description = "마케팅 수신 동의 (선택)", example = "true")
    @NotNull(message = "마케팅 수신 동의 여부를 선택해주세요.")
    private Boolean adAllow;
}