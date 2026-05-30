package com.umcstudy.jace.domain.user.service;

import com.umcstudy.jace.domain.user.dto.UserResDTO;
import com.umcstudy.jace.domain.user.entity.RefreshToken;
import com.umcstudy.jace.domain.user.exception.UserException;
import com.umcstudy.jace.domain.user.exception.code.UserErrorCode;
import com.umcstudy.jace.domain.user.repository.RefreshTokenRepository;
import com.umcstudy.jace.global.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public String issue(Long userId) {
        String token = jwtTokenProvider.generateRefreshToken(userId);
        LocalDateTime expiresAt = LocalDateTime.now()
                .plusSeconds(jwtTokenProvider.getRefreshExpirationMs() / 1000);

        refreshTokenRepository.findByUserId(userId)
                .ifPresentOrElse(
                        rt -> rt.rotate(token, expiresAt),
                        () -> refreshTokenRepository.save(RefreshToken.builder()
                                .userId(userId)
                                .token(token)
                                .expiresAt(expiresAt)
                                .build())
                );

        return token;
    }

    @Transactional
    public UserResDTO.TokenReissue reissue(String refreshToken) {
        if (!jwtTokenProvider.validateRefreshToken(refreshToken)) {
            throw new UserException(UserErrorCode.INVALID_REFRESH_TOKEN);
        }

        RefreshToken stored = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new UserException(UserErrorCode.REFRESH_TOKEN_NOT_FOUND));

        if (stored.getExpiresAt().isBefore(LocalDateTime.now())) {
            refreshTokenRepository.delete(stored);
            throw new UserException(UserErrorCode.REFRESH_TOKEN_EXPIRED);
        }

        Long userId = stored.getUserId();
        String newAccessToken = jwtTokenProvider.generateToken(userId);
        String newRefreshToken = issue(userId);

        return UserResDTO.TokenReissue.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .tokenType("Bearer")
                .build();
    }
}
