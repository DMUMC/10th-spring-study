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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
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

        Slice<Mission> missions = missionRepository.findByRegionWithCursor(region, cursorId,
                PageRequest.of(0, size, Sort.by(Sort.Direction.DESC, "id")));

        List<MissionResDTO.MissionItem> missionList = missions.getContent().stream()
                .map(MissionConverter::toMissionItem)
                .toList();

        return MissionConverter.toGetHome(clearMissionCnt, missionList, missions.hasNext());
    }

    @Transactional(readOnly = true)
    public MissionResDTO.GetMyMission getMyMission(MissionStatus missionCondition, int page, int size) {
        Long userId = SecurityUtils.getCurrentUserId();

        Page<MissionUser> missionUsers = missionUserRepository.findByUserIdAndCondition(
                userId, missionCondition, PageRequest.of(page, size));

        return MissionConverter.toGetMyMission(missionUsers);
    }

    public MissionResDTO.PatchMissionSuc patchMissionSuc(Integer missionId) {
        return null;
    }
}
