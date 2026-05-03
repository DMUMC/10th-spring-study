package com.UmcSpringStudy.jingjing2.domain.mission.service;

import com.UmcSpringStudy.jingjing2.domain.mission.converter.MissionConverter;
import com.UmcSpringStudy.jingjing2.domain.mission.dto.request.MissionCreateRequest;
import com.UmcSpringStudy.jingjing2.domain.mission.dto.response.MissionDetailResponse;
import com.UmcSpringStudy.jingjing2.domain.mission.entity.Mission;
import com.UmcSpringStudy.jingjing2.domain.mission.repository.MissionRepository;
import com.UmcSpringStudy.jingjing2.domain.store.entity.Store;
import com.UmcSpringStudy.jingjing2.domain.store.repository.StoreRepository;
import com.UmcSpringStudy.jingjing2.global.exception.CustomException;
import com.UmcSpringStudy.jingjing2.global.exception.errorcodes.StoreErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public MissionDetailResponse createMission(MissionCreateRequest request) {
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new CustomException(StoreErrorCode.STORE_NOT_FOUND));

        Mission newMission = MissionConverter.toEntity(request, store);
        return MissionConverter.toDetailResponse(missionRepository.save(newMission));
    }
}