package com.umc.jaengchalttak.domain.store.service;

import com.umc.jaengchalttak.domain.mission.entity.Mission;
import com.umc.jaengchalttak.domain.mission.repository.MissionRepository;
import com.umc.jaengchalttak.domain.store.converter.StoreMissionConverter;
import com.umc.jaengchalttak.domain.store.dto.request.CreateStoreMissionReqDTO;
import com.umc.jaengchalttak.domain.store.dto.response.GetStoreMissionResDTO;
import com.umc.jaengchalttak.domain.store.entity.Store;
import com.umc.jaengchalttak.domain.store.payload.StoreException;
import com.umc.jaengchalttak.domain.store.payload.code.StoreErrorCode;
import com.umc.jaengchalttak.domain.store.repository.StoreRepository;
import com.umc.jaengchalttak.global.converter.GlobalConverter;
import com.umc.jaengchalttak.global.dto.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreMissionService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    @Transactional
    public void createMission(Long storeId, CreateStoreMissionReqDTO request) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Mission mission = StoreMissionConverter.toMission(store, request);
        missionRepository.save(mission);
    }

    @Transactional(readOnly = true)
    public Pagination<GetStoreMissionResDTO> getMissions(
            Long storeId,
            Integer pageSize,
            Integer pageNumber,
            String sort
    ) {
        Sort sortInfo = (sort != null && !sort.isBlank()) ?
                Sort.by(sort) :
                Sort.by("id").descending();

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

        Page<Mission> missionList = missionRepository.findAllByStoreId(storeId, pageRequest);

        return GlobalConverter.toPagination(
                missionList.map(StoreMissionConverter::toGetStoreMissionResDTO).toList(),
                missionList.getNumber(),
                missionList.getSize()
        );
    }

}
