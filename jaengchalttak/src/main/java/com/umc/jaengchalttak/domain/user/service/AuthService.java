package com.umc.jaengchalttak.domain.user.service;

import com.umc.jaengchalttak.domain.user.converter.UserConverter;
import com.umc.jaengchalttak.domain.user.dto.request.SignUpReqDTO;
import com.umc.jaengchalttak.domain.user.entity.User;
import com.umc.jaengchalttak.domain.user.repository.FavoriteFoodRepository;
import com.umc.jaengchalttak.domain.user.repository.ServiceUseAllowRepository;
import com.umc.jaengchalttak.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final ServiceUseAllowRepository serviceUseAllowRepository;
    private final FavoriteFoodRepository favoriteFoodRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createUser(SignUpReqDTO request) {
        String encodedPassword = passwordEncoder.encode(request.password());

        // User 생성
        User user = UserConverter.toUser(request, encodedPassword);
        userRepository.save(user);

        // 약관 동의 저장
        serviceUseAllowRepository.saveAll(UserConverter.toServiceUseAllows(request, user));

        // 선호 음식 저장
        favoriteFoodRepository.saveAll(UserConverter.toFavoriteFoods(request, user));
    }

    @Transactional(readOnly = true)
    public boolean isEmailDuplicated(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public boolean isNameDuplicated(String name) {
        return userRepository.existsByName(name);
    }

}
