package com.umcstudy.jace.domain.user.dto;

import com.umcstudy.jace.domain.user.enums.Gender;
import com.umcstudy.jace.domain.user.enums.SocialProvider;

import java.time.LocalDate;
import java.util.List;

public class UserReqDTO {

    public record PostSignup(
            List<TermsDTO> termsList,
            String name,
            Gender gender,
            LocalDate birth,
            Integer zipcode,
            String address,
            String addressDtl,
            List<Long> favoriteFoodList,
            SocialProvider socialProvider,
            String socialId
    ){}

    public record TermsDTO(
            Long termsId,
            Boolean isAgree
    ){}
}
