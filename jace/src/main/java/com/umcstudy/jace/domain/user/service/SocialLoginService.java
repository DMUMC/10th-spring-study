package com.umcstudy.jace.domain.user.service;

import com.umcstudy.jace.domain.user.dto.UserReqDTO;
import com.umcstudy.jace.domain.user.dto.UserResDTO;
import com.umcstudy.jace.domain.user.exception.UserException;
import com.umcstudy.jace.domain.user.exception.code.UserErrorCode;
import com.umcstudy.jace.domain.user.repository.UserSocialRepository;
import com.umcstudy.jace.global.client.OAuthClient;
import com.umcstudy.jace.global.client.SocialUserInfo;
import com.umcstudy.jace.global.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SocialLoginService {

    private final List<OAuthClient> oAuthClients;
    private final UserSocialRepository userSocialRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public UserResDTO.SocialLogin login(UserReqDTO.SocialLogin dto) {
        OAuthClient oAuthClient = oAuthClients.stream()
                .filter(client -> client.getProvider() == dto.provider())
                .findFirst()
                .orElseThrow(() -> new UserException(UserErrorCode.UNSUPPORTED_SOCIAL_PROVIDER));

        SocialUserInfo userInfo = oAuthClient.getUserInfo(dto.socialAccessToken());

        return userSocialRepository
                .findByProviderAndProviderUserId(dto.provider(), userInfo.providerUserId())
                .map(userSocial -> {
                    // 기존 회원 → JWT 발급
                    String token = jwtTokenProvider.generateToken(userSocial.getUser().getId());
                    return new UserResDTO.SocialLogin(false, token, "Bearer", null, null, null, null);
                })
                .orElseGet(() ->
                    // 신규 유저 → 회원가입 유도
                    new UserResDTO.SocialLogin(true, null, null,
                            userInfo.email(), userInfo.name(),
                            dto.provider(), userInfo.providerUserId())
                );
    }
}
