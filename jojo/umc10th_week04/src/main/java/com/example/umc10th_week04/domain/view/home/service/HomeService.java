package com.example.umc10th_week04.domain.view.home.service;

import com.example.umc10th_week04.domain.mission.entity.Mission;
import com.example.umc10th_week04.domain.mission.entity.UserMission;
import com.example.umc10th_week04.domain.mission.repository.MissionRepository;
import com.example.umc10th_week04.domain.mission.repository.UserMissionRepository;
import com.example.umc10th_week04.domain.user.entity.User;
import com.example.umc10th_week04.domain.user.exception.UserException;
import com.example.umc10th_week04.domain.user.exception.code.UserErrorCode;
import com.example.umc10th_week04.domain.user.repository.UserRepository;
import com.example.umc10th_week04.domain.view.home.converter.HomeConverter;
import com.example.umc10th_week04.domain.view.home.dto.HomeResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HomeService {

    private static final int DEFAULT_PAGE_SIZE = 10;

    private final UserRepository userRepository;
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;

    public HomeResDTO.GetInfo getHome(Long userId, String location) {
        Pageable pageable = PageRequest.of(0, DEFAULT_PAGE_SIZE);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        List<Mission> availableMissions = missionRepository.findAvailableMissionsByLocation(location, pageable);
        List<UserMission> completedMissions = userMissionRepository.findCompletedMissionsByUserIdAndLocation(
                userId,
                location,
                pageable
        );

        return HomeConverter.toGetInfo(user, location, availableMissions, completedMissions);
    }
}
