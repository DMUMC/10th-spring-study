package com.example.umc10th_week04.domain.user.service;

import com.example.umc10th_week04.domain.user.converter.UserConverter;
import com.example.umc10th_week04.domain.user.dto.UserResDTO;
import com.example.umc10th_week04.domain.user.entity.User;
import com.example.umc10th_week04.domain.user.exception.UserException;
import com.example.umc10th_week04.domain.user.exception.code.UserErrorCode;
import com.example.umc10th_week04.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserResDTO.UserInfo getUserInfo(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        return UserConverter.toUserInfo(user);
    }
}
