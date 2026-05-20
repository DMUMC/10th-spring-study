package com.umc.jaengchalttak.domain.user.dto.request;

import com.umc.jaengchalttak.domain.user.enums.Address;
import com.umc.jaengchalttak.domain.user.enums.FoodName;
import com.umc.jaengchalttak.domain.user.enums.Gender;
import com.umc.jaengchalttak.domain.user.enums.ServiceUseTitle;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

@Schema(description = "회원가입 관련 정보를 담은 DTO")
public record SignUpReqDTO(

        @Email(message = "올바른 이메일 형식이 아닙니다.")
        @NotBlank(message = "이메일은 필수입니다.")
        @Schema(description = "회원 이메일", example = "test@example.com")
        String email,

        @NotEmpty(message = "약관 동의 정보는 필수입니다.")
        @Schema(description = "서비스 이용 약관 동의 여부",
                example = """
                        {
                          "AGE_OVER_14": true,
                          "TERMS_OF_SERVICE": true,
                          "PRIVACY_POLICY": true,
                          "LOCATION_SERVICE": false,
                          "MARKETING": false
                        }
                        """
        )
        Map<ServiceUseTitle, Boolean> serviceUseAllow,

        @NotBlank(message = "이름은 필수입니다.")
        @Size(max = 45, message = "이름은 45자 이내여야 합니다.")
        @Schema(description = "회원 이름", example = "홍길동")
        String name,

        @NotBlank(message = "비밀번호는 필수입니다.")
        @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하여야 합니다.")
        @Pattern(
                regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[\\W_])(?!.*\\s).{8,20}$",
                message = "비밀번호는 8~20자의 영문, 숫자, 특수문자를 포함하며 공백을 사용할 수 없습니다."
        )
        @Schema(description = "회원 비밀번호", example = "qwer1234!")
        String password,

        @Schema(description = "회원 성별", example = "MALE", allowableValues = {"MALE", "FEMALE"})
        Gender gender,

        @NotNull(message = "생년월일은 필수입니다.")
        @Past(message = "생년월일은 과거 날짜여야 합니다.")
        @Schema(description = "회원 생년월일", example = "2000-01-01")
        LocalDate birthday,

        @Valid
        @NotNull(message = "주소는 필수입니다.")
        @Schema(description = "회원 주소 정보")
        Address address,

        @Pattern(regexp = "^01[0-9]-?\\d{3,4}-?\\d{4}$", message = "올바른 휴대폰 번호 형식이 아닙니다.")
        @Schema(description = "회원 휴대폰 번호", example = "010-1234-5678")
        String phoneNumber,

        @NotEmpty(message = "선호 음식은 최소 1개 이상 선택해야 합니다.")
        @Schema(description = "선호 음식 목록",
                example = """
                        [
                          "KOREAN_FOOD",
                          "CHICKEN",
                          "DESSERT"
                        ]
                        """
        )
        Set<FoodName> favoriteFoods
) {
}