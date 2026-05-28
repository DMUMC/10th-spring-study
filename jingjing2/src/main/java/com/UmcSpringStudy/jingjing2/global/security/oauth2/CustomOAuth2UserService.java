package com.UmcSpringStudy.jingjing2.global.security.oauth2;

import com.UmcSpringStudy.jingjing2.domain.user.converter.UserConverter;
import com.UmcSpringStudy.jingjing2.domain.user.entity.User;
import com.UmcSpringStudy.jingjing2.domain.user.enums.Provider;
import com.UmcSpringStudy.jingjing2.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Provider provider = Provider.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());

        String socialId = Objects.requireNonNull(oAuth2User.getAttribute("id")).toString();

        // DB 확인 및 없으면 생성 (초기 가입)
        User user = userRepository.findByProviderAndSub(provider, socialId)
                .orElseGet(() -> userRepository.save(UserConverter.toOAuth2User(provider, socialId)));

        Map<String, Object> attributes = new HashMap<>(oAuth2User.getAttributes());
        attributes.put("dbUserId", user.getId()); // 핸들러에서 사용하기 위해 PK 주입

        return new DefaultOAuth2User(Collections.emptyList(), attributes, "id");
    }
}