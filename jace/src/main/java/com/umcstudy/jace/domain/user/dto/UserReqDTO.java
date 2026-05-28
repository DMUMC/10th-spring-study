package com.umcstudy.jace.domain.user.dto;

import com.umcstudy.jace.domain.user.enums.Gender;
import com.umcstudy.jace.domain.user.enums.SocialProvider;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public class UserReqDTO {

    public record PostSignup(
            @Valid @NotEmpty(message = "약관 목록은 필수입니다") List<TermsDTO> termsList,
            @NotBlank(message = "이름은 필수입니다") @Size(max = 50, message = "이름은 50자 이하여야 합니다") String name,
            @Email(message = "올바른 이메일 형식이 아닙니다") String email,
            @NotNull(message = "성별은 필수입니다") Gender gender,
            @NotNull(message = "생년월일은 필수입니다") @Past(message = "생년월일은 과거 날짜여야 합니다") LocalDate birth,
            @NotBlank(message = "우편번호는 필수입니다") @Pattern(regexp = "\\d{5}", message = "우편번호는 5자리 숫자여야 합니다") String zipcode,
            @NotBlank(message = "주소는 필수입니다") String address,
            String addressDtl,
            @NotEmpty(message = "선호 음식은 최소 1개 이상 선택해야 합니다") List<Long> favoriteFoodList,
            @NotNull(message = "소셜 제공자는 필수입니다") SocialProvider socialProvider,
            @NotBlank(message = "소셜 ID는 필수입니다") String socialId
    ){}

    public record TermsDTO(
            @NotNull(message = "약관 ID는 필수입니다") Long termsId,
            @NotNull(message = "약관 동의 여부는 필수입니다") Boolean isAgree
    ){}

    public record SocialLogin(
            @NotNull(message = "소셜 제공자는 필수입니다") SocialProvider provider,
            @NotBlank(message = "소셜 액세스 토큰은 필수입니다") String socialAccessToken
    ) {}

    public record FormLogin(
            @NotBlank(message = "이메일은 필수입니다") @Email(message = "올바른 이메일 형식이 아닙니다") String email,
            @NotBlank(message = "비밀번호는 필수입니다") String password
    ) {}

    public record TokenReissue(
            @NotBlank(message = "리프레시 토큰은 필수입니다") String refreshToken
    ) {}

    public record FormSignup(
            @Valid @NotEmpty(message = "약관 목록은 필수입니다") List<TermsDTO> termsList,
            @NotBlank(message = "이름은 필수입니다") @Size(max = 50, message = "이름은 50자 이하여야 합니다") String name,
            @NotBlank(message = "이메일은 필수입니다") @Email(message = "올바른 이메일 형식이 아닙니다") String email,
            @NotBlank(message = "비밀번호는 필수입니다") @Size(min = 8, message = "비밀번호는 8자 이상이어야 합니다") String password,
            @NotNull(message = "성별은 필수입니다") Gender gender,
            @NotNull(message = "생년월일은 필수입니다") @Past(message = "생년월일은 과거 날짜여야 합니다") LocalDate birth,
            @NotBlank(message = "우편번호는 필수입니다") @Pattern(regexp = "\\d{5}", message = "우편번호는 5자리 숫자여야 합니다") String zipcode,
            @NotBlank(message = "주소는 필수입니다") String address,
            String addressDtl,
            @NotEmpty(message = "선호 음식은 최소 1개 이상 선택해야 합니다") List<Long> favoriteFoodList
    ) {}
}
