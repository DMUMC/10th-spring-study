package com.example.umc10th_week04.domain.user.service;

import com.example.umc10th_week04.domain.user.converter.UserConverter;
import com.example.umc10th_week04.domain.user.dto.UserReqDTO;
import com.example.umc10th_week04.domain.user.dto.UserResDTO;
import com.example.umc10th_week04.domain.user.entity.User;
import com.example.umc10th_week04.domain.user.exception.UserException;
import com.example.umc10th_week04.domain.user.exception.code.UserErrorCode;
import com.example.umc10th_week04.domain.user.repository.UserRepository;
import com.example.umc10th_week04.global.security.entity.AuthUser;
import com.example.umc10th_week04.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserResDTO.UserInfo getUserInfo(
            AuthUser authUser
    ){
        User user = userRepository.findById(authUser.getUser().getId())
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
        return UserConverter.toUserInfo(user);
    }

    public UserResDTO.Login login(
            UserReqDTO.Login dto
    ) {
        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new UserException(UserErrorCode.INVALID_PASSWORD);
        }

        AuthUser authUser = new AuthUser(user);

        String accessToken = jwtUtil.createAccessToken(
                authUser.getUser().getId(),
                authUser.getUser().getName(),
                authUser.getUser().getEmail(),
                authUser.getUser().getSocialType(),
                authUser.getUser().getSocialUid(),
                authUser.getAuthorities()
        );

        return UserConverter.toLogin(accessToken);
    }

    @Transactional
    public UserResDTO.Signup signup(UserReqDTO.SignupInfo dto){

        // Email 중복 확인 로직
        if(userRepository.existsByEmail(dto.email())) {
            throw new UserException(UserErrorCode.EMAIL_ALREADY_EXISTS);
        }

        // Password Salting
        String encodedPassword = passwordEncoder.encode(dto.password());

        // UserReqDTO -> User Entity로 Converting
        User user = UserConverter.toUser(dto, encodedPassword);

        // DB에 User 정보 저장
        userRepository.save(user);

        AuthUser authUser = new AuthUser(user);

        // 액세스 토큰 발급
        String accessToken = jwtUtil.createAccessToken(
                authUser.getUser().getId(),
                authUser.getUser().getName(),
                authUser.getUser().getEmail(),
                authUser.getUser().getSocialType(),
                authUser.getUser().getSocialUid(),
                authUser.getAuthorities()
        );

        // 확인용 UserResDTO return
        return UserConverter.toSignup(accessToken);
    }


}
