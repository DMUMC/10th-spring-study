package com.umc.jaengchalttak.domain.user.service;

import com.umc.jaengchalttak.domain.user.converter.UserConverter;
import com.umc.jaengchalttak.domain.user.dto.request.LoginReqDTO;
import com.umc.jaengchalttak.domain.user.dto.request.SignUpReqDTO;
import com.umc.jaengchalttak.domain.user.dto.response.LoginResDTO;
import com.umc.jaengchalttak.domain.user.entity.User;
import com.umc.jaengchalttak.domain.user.payload.UserException;
import com.umc.jaengchalttak.domain.user.payload.code.UserErrorCode;
import com.umc.jaengchalttak.domain.user.repository.FavoriteFoodRepository;
import com.umc.jaengchalttak.domain.user.repository.ServiceUseAllowRepository;
import com.umc.jaengchalttak.domain.user.repository.UserRepository;
import com.umc.jaengchalttak.global.security.entity.AuthUser;
import com.umc.jaengchalttak.global.security.service.CustomUserDetailsService;
import com.umc.jaengchalttak.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public LoginResDTO login(LoginReqDTO request) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                );

        Authentication authentication =
                authenticationManager.authenticate(authToken);

        AuthUser authUser = (AuthUser) authentication.getPrincipal();

        String accessToken = jwtUtil.createAccessToken(authUser);

        return new LoginResDTO(accessToken);
    }

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
