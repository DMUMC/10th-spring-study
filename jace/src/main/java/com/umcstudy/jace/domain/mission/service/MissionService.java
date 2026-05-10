package com.umcstudy.jace.domain.mission.service;

import com.umcstudy.jace.domain.mission.converter.MissionConverter;
import com.umcstudy.jace.domain.mission.dto.MissionResDTO;
import com.umcstudy.jace.domain.mission.entity.Mission;
import com.umcstudy.jace.domain.mission.entity.mapping.MissionUser;
import com.umcstudy.jace.domain.mission.enums.MissionStatus;
import com.umcstudy.jace.domain.mission.repository.MissionRepository;
import com.umcstudy.jace.domain.mission.repository.MissionUserRepository;
import com.umcstudy.jace.global.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final MissionUserRepository missionUserRepository;

    @Transactional(readOnly = true)
    public MissionResDTO.GetHome getHome(String region, Long cursorId, int size) {
        Long userId = SecurityUtils.getCurrentUserId();

        long clearMissionCnt = missionUserRepository.countByUser_IdAndMissionCondition(userId, MissionStatus.SUCCESS);

        List<Mission> missions = missionRepository.findByRegionWithCursor(region, cursorId, PageRequest.of(0, size + 1));
        boolean hasNext = missions.size() > size;
        if (hasNext) {
            missions = missions.subList(0, size);
        }

        List<MissionResDTO.MissionItem> missionList = missions.stream()
                .map(MissionConverter::toMissionItem)
                .toList();

        return MissionConverter.toGetHome(clearMissionCnt, missionList, hasNext);
    }

    @Transactional(readOnly = true)
    public MissionResDTO.GetMyMission getMyMission(MissionStatus missionCondition, Long cursorId, int size) {
        Long userId = SecurityUtils.getCurrentUserId();

        List<MissionUser> missionUsers = missionUserRepository.findByUserIdAndConditionWithCursor(
                userId, missionCondition, cursorId, PageRequest.of(0, size + 1));
        boolean hasNext = missionUsers.size() > size;
        if (hasNext) {
            missionUsers = missionUsers.subList(0, size);
        }

        List<MissionResDTO.MyMissionItem> missionList = missionUsers.stream()
                .map(MissionConverter::toMyMissionItem)
                .toList();

        return MissionConverter.toGetMyMission(missionList, hasNext);
    }

    public MissionResDTO.PatchMissionSuc patchMissionSuc(Integer missionId) {
        return null;
    }
}
