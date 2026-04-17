package com.umcstudy.jace.domain.mission.service;

import com.umcstudy.jace.domain.mission.dto.MissionReqDTO;
import com.umcstudy.jace.domain.mission.dto.MissionResDTO;
import com.umcstudy.jace.domain.mission.enums.MissionStatus;
import org.springframework.stereotype.Service;

@Service
public class MissionService {
    public MissionResDTO.GetHome getHome(MissionReqDTO.GetHome dto) {
        return null;
    }

    public MissionResDTO.GetMyMission getMyMission(MissionStatus missionCondition) {
        return null;
    }

    public MissionResDTO.PatchMissionSuc patchMissionSuc(Integer missionId) {
        return null;
    }
}
