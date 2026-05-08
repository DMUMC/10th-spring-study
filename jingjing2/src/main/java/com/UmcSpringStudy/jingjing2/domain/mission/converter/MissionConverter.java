package com.UmcSpringStudy.jingjing2.domain.mission.converter;

import com.UmcSpringStudy.jingjing2.domain.mission.dto.request.MissionCreateRequest;
import com.UmcSpringStudy.jingjing2.domain.mission.entity.Mission;
import com.UmcSpringStudy.jingjing2.domain.mission.dto.response.MissionDetailResponse;
import com.UmcSpringStudy.jingjing2.domain.mission.dto.response.MissionPreviewResponse;
import com.UmcSpringStudy.jingjing2.domain.store.entity.Store;
import com.UmcSpringStudy.jingjing2.domain.user.dto.misson.response.UserMissionPreviewResponse;
import com.UmcSpringStudy.jingjing2.domain.user.entity.UserMission;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    // 1. 단일 엔티티 -> MissionPreviewResponse
    public static MissionPreviewResponse toPreviewResponse(Mission mission) {
        return MissionPreviewResponse.builder()
                .missionId(mission.getId())
                .name(mission.getName())
                .reward(mission.getReward())
                .expDate(mission.getExpDate())
                .storeName(mission.getStore().getName())
                .storeCategory(mission.getStore().getCategory())
                .build();
    }

    // 2. Mission 리스트 -> MissionPreviewResponse 리스트
    public static List<MissionPreviewResponse> toPreviewResponseList(List<Mission> missions) {
        return missions.stream()
                .map(MissionConverter::toPreviewResponse)
                .collect(Collectors.toList());
    }

    // 3. UserMission 리스트 -> MissionPreviewResponse 리스트
    public static List<MissionPreviewResponse> fromUserMissionList(List<UserMission> userMissions) {
        return userMissions.stream()
                .map(um -> toPreviewResponse(um.getMission()))
                .collect(Collectors.toList());
    }

    // 4. Page<UserMission> -> UserMissionPreviewResponse (추가됨)
    public static UserMissionPreviewResponse toUserMissionPreviewResponse(Page<UserMission> userMissionPage) {
        return UserMissionPreviewResponse.builder()
                .missionList(fromUserMissionList(userMissionPage.getContent()))
                .totalPage(userMissionPage.getTotalPages())
                .totalElements(userMissionPage.getTotalElements())
                .isFirst(userMissionPage.isFirst())
                .isLast(userMissionPage.isLast())
                .build();
    }

    // 5. 상세 정보 및 엔티티 변환
    public static MissionDetailResponse toDetailResponse(Mission mission) {
        return MissionDetailResponse.builder()
                .missionId(mission.getId())
                .name(mission.getName())
                .context(mission.getContext())
                .rate(mission.getRate())
                .taskCount(mission.getTaskCount())
                .reward(mission.getReward())
                .expDate(mission.getExpDate())
                .storeName(mission.getStore().getName())
                .storeCategory(mission.getStore().getCategory())
                .build();
    }

    public static Mission toEntity(MissionCreateRequest request, Store store) {
        Mission mission = new Mission();
        mission.setName(request.getName());
        mission.setContext(request.getContext());
        mission.setRate(request.getRate());
        mission.setTaskCount(request.getTaskCount());
        mission.setReward(request.getReward());
        mission.setExpDate(request.getExpDate());
        mission.setStore(store);
        return mission;
    }
}