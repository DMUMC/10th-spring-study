package com.umcstudy.jace.domain.mission.service;

import com.umcstudy.jace.domain.mission.converter.MissionConverter;
import com.umcstudy.jace.domain.mission.dto.MissionResDTO;
import com.umcstudy.jace.domain.mission.entity.Mission;
import com.umcstudy.jace.domain.mission.enums.MissionStatus;
import com.umcstudy.jace.domain.mission.repository.MissionRepository;
import com.umcstudy.jace.domain.mission.repository.MissionUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final MissionUserRepository missionUserRepository;

    @Transactional(readOnly = true)
    public MissionResDTO.GetHome getHome(String region, Long cursorId, int size) {
        Long userId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());

        long clearMissionCnt = missionUserRepository.countByUser_IdAndMissionCondition(userId, MissionStatus.SUCCESS);

        List<Mission> missions = missionRepository.findByRegionWithCursor(region, cursorId, PageRequest.of(0, size + 1));
        boolean hasNext = missions.size() > size;
        if (hasNext) {
            missions = missions.subList(0, size);
        }

        List<MissionResDTO.MissionItem> missionList = missions.stream()
                .map(MissionConverter::toMissionItem)
                .collect(Collectors.toList());

        return MissionConverter.toGetHome(clearMissionCnt, missionList, hasNext);
    }

    public MissionResDTO.GetMyMission getMyMission(MissionStatus missionCondition) {
        return null;
    }

    public MissionResDTO.PatchMissionSuc patchMissionSuc(Integer missionId) {
        return null;
    }
}
