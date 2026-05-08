package com.example.umc10th_week04.domain.mission.service;

import com.example.umc10th_week04.domain.mission.converter.MissionConverter;
import com.example.umc10th_week04.domain.mission.dto.MissionResDTO;
import com.example.umc10th_week04.domain.mission.entity.UserMission;
import com.example.umc10th_week04.domain.mission.repository.UserMissionRepository;
import com.example.umc10th_week04.domain.user.exception.UserException;
import com.example.umc10th_week04.domain.user.exception.code.UserErrorCode;
import com.example.umc10th_week04.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private static final int DEFAULT_PAGE_SIZE = 10;

    private final UserMissionRepository userMissionRepository;
    private final UserRepository userRepository;

    public MissionResDTO.MyMission getMyMissions(Long userId) {
        return getMyMissions(userId, PageRequest.of(0, DEFAULT_PAGE_SIZE));
    }

    public MissionResDTO.MyMission getMyMissions(Long userId, Pageable pageable) {
        if (!userRepository.existsById(userId)) {
            throw new UserException(UserErrorCode.USER_NOT_FOUND);
        }

        List<UserMission> userMissions = userMissionRepository.findUserMissionsByUserId(
                userId,
                pageable
        );

        return MissionConverter.toGetMission(userMissions);
    }
}
