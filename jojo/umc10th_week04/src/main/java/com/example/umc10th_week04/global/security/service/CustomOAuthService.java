package com.example.umc10th_week04.global.security.service;

import com.example.umc10th_week04.domain.user.converter.UserConverter;
import com.example.umc10th_week04.domain.user.entity.User;
import com.example.umc10th_week04.domain.user.enums.SocialType;
import com.example.umc10th_week04.domain.user.exception.UserException;
import com.example.umc10th_week04.domain.user.exception.code.UserErrorCode;
import com.example.umc10th_week04.domain.user.repository.UserRepository;
import com.example.umc10th_week04.global.security.dto.KakaoDTO;
import com.example.umc10th_week04.global.security.dto.OAuthDTO;
import com.example.umc10th_week04.global.security.entity.OAuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomOAuthService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public OAuth2User loadUser(
            OAuth2UserRequest userRequest
    ) throws OAuth2AuthenticationException {

        // (필수) 인증 서버의 일회성 토큰을 이용해 정보 조회 & 유저 객체 생성
        OAuth2User oAuthUser = super.loadUser(userRequest);

        // OAuth 공통 정보 DTO로 매핑
        SocialType providerId = getProviderId(userRequest);
        Map<String, Object> attributes = oAuthUser.getAttributes();
        OAuthDTO dto = getOAuthDTO(providerId, attributes);

        // DB 저장 : 있다면 그 데이터 가져오고 없으면 새로 저장
        User user = userRepository.findBySocialTypeAndSocialUid(dto.getSocialType(), dto.getSocialUid())
                .orElseGet(() -> {
                    User newUser = UserConverter.toUser(dto);
                    userRepository.save(newUser);
                    return newUser;
                });
        return new OAuthUser(user, oAuthUser.getAttributes());
    }

    private SocialType getProviderId(OAuth2UserRequest userRequest) {
        try {
            return SocialType.valueOf(
                    userRequest.getClientRegistration()
                            .getRegistrationId()
                            .toUpperCase()
            );
        } catch (IllegalArgumentException e) {
            throw new UserException(UserErrorCode.NOT_SUPPORT_SOCIAL_PROVIDER);
        }
    }

    private OAuthDTO getOAuthDTO(
            SocialType providerId,
            Map<String, Object> attributes
    ) {
        return switch (providerId) {
            case KAKAO -> getKakaoDTO(attributes);
            case GOOGLE -> getGoogleDTO(attributes);
            case NAVER -> getNaverDTO(attributes);
            default -> throw new UserException(UserErrorCode.NOT_SUPPORT_SOCIAL_PROVIDER);
        };
    }

    private OAuthDTO getKakaoDTO(Map<String, Object> attributes) {
        String socialUid = attributes.get("id").toString();
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

        String email = kakaoAccount.get("email").toString();
        String name = profile.get("nickname").toString();

        return new KakaoDTO(socialUid, email, name);
    }

    private OAuthDTO getGoogleDTO(Map<String, Object> attributes) {
        throw new UserException(UserErrorCode.NOT_SUPPORT_SOCIAL_PROVIDER);
    }

    private OAuthDTO getNaverDTO(Map<String, Object> attributes) {
        throw new UserException(UserErrorCode.NOT_SUPPORT_SOCIAL_PROVIDER);
    }
}
