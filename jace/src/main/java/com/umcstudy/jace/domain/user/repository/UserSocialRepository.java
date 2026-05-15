package com.umcstudy.jace.domain.user.repository;

import com.umcstudy.jace.domain.user.entity.mapping.UserSocial;
import com.umcstudy.jace.domain.user.enums.SocialProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSocialRepository extends JpaRepository<UserSocial, Long> {
    Optional<UserSocial> findByProviderAndProviderUserId(SocialProvider provider, String providerUserId);
}
