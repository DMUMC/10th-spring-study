package com.UmcSpringStudy.jingjing2.domain.user.service;

import com.UmcSpringStudy.jingjing2.domain.mission.converter.MissionConverter;
import com.UmcSpringStudy.jingjing2.domain.mission.dto.response.MissionPreviewResponse;
import com.UmcSpringStudy.jingjing2.domain.mission.entity.Mission;
import com.UmcSpringStudy.jingjing2.domain.mission.repository.MissionRepository;
import com.UmcSpringStudy.jingjing2.domain.store.repository.RegionRepository;
import com.UmcSpringStudy.jingjing2.domain.user.converter.UserMissionConverter;
import com.UmcSpringStudy.jingjing2.domain.user.dto.misson.request.UserMissionUpdateRequest;
import com.UmcSpringStudy.jingjing2.domain.user.dto.misson.response.UserMissionPreviewResponse;
import com.UmcSpringStudy.jingjing2.domain.user.dto.misson.response.UserMissionResponse;
import com.UmcSpringStudy.jingjing2.domain.user.entity.User;
import com.UmcSpringStudy.jingjing2.domain.user.entity.UserMission;
import com.UmcSpringStudy.jingjing2.domain.user.enums.MissionStatus;
import com.UmcSpringStudy.jingjing2.domain.user.repository.UserMissionRepository;
import com.UmcSpringStudy.jingjing2.domain.user.repository.UserRepository;
import com.UmcSpringStudy.jingjing2.global.exception.CustomException;
import com.UmcSpringStudy.jingjing2.global.exception.errorcodes.CommonErrorCode;
import com.UmcSpringStudy.jingjing2.global.exception.errorcodes.MissionErrorCode;
import com.UmcSpringStudy.jingjing2.global.exception.errorcodes.UserErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserMissionService {

    private final UserMissionRepository userMissionRepository;
    private final UserRepository userRepository;
    private final MissionRepository missionRepository;
    private final RegionRepository regionRepository;

    public List<MissionPreviewResponse> getAvailableMissionsByRegion(Long userId, Long regionId) {
        if (!regionRepository.existsById(regionId)) {
            throw new CustomException(CommonErrorCode.RESOURCE_NOT_FOUND);
        }

        List<Mission> allMissionsInRegion = missionRepository.findAllByRegionId(regionId);

        Set<Long> challengedMissionIds = userMissionRepository.findAllByUserId(userId, Pageable.unpaged())
                .getContent().stream()
                .map(um -> um.getMission().getId())
                .collect(Collectors.toSet());

        List<Mission> availableMissions = allMissionsInRegion.stream()
                .filter(mission -> !challengedMissionIds.contains(mission.getId()))
                .collect(Collectors.toList());

        return MissionConverter.toPreviewResponseList(availableMissions);
    }

    public UserMissionPreviewResponse getUserMissions(Long userId, int page, String option) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<UserMission> userMissionPage;

        if ("All".equalsIgnoreCase(option)) {
            userMissionPage = userMissionRepository.findAllByUserId(userId, pageRequest);
        } else {
            try {
                MissionStatus status = MissionStatus.valueOf(option.toUpperCase());
                userMissionPage = userMissionRepository.findAllByUserIdAndIsComplete(userId, status, pageRequest);
            } catch (IllegalArgumentException e) {
                throw new CustomException(CommonErrorCode.INVALID_PARAMETER);
            }
        }
        return MissionConverter.toUserMissionPreviewResponse(userMissionPage);
    }

    @Transactional
    public UserMissionResponse addMission(Long userId, Long missionId) {
        userMissionRepository.findByUserIdAndMissionId(userId, missionId)
                .ifPresent(m -> { throw new CustomException(MissionErrorCode.ALREADY_PARTICIPATED); });

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new CustomException(MissionErrorCode.MISSION_NOT_FOUND));

        UserMission userMission = UserMissionConverter.toUserMission(user, mission);
        return UserMissionConverter.toUserMissionResponse(userMissionRepository.save(userMission));
    }

    @Transactional
    public UserMissionResponse updateMission(Long userId, Long missionId, UserMissionUpdateRequest request) {
        UserMission userMission = userMissionRepository.findByUserIdAndMissionId(userId, missionId)
                .orElseThrow(() -> new CustomException(CommonErrorCode.RESOURCE_NOT_FOUND));

        if (request.getStatus() != null) userMission.setIsComplete(request.getStatus());
        if (request.getProgress() != null) userMission.setProgress(request.getProgress());

        return UserMissionConverter.toUserMissionResponse(userMission);
    }
}