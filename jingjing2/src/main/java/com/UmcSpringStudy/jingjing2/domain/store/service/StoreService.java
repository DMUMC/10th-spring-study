package com.UmcSpringStudy.jingjing2.domain.store.service;

import com.UmcSpringStudy.jingjing2.domain.store.converter.StoreConverter;
import com.UmcSpringStudy.jingjing2.domain.store.entity.Region;
import com.UmcSpringStudy.jingjing2.domain.store.entity.Store;
import com.UmcSpringStudy.jingjing2.domain.store.repository.RegionRepository;
import com.UmcSpringStudy.jingjing2.domain.store.repository.StoreRepository;
import com.UmcSpringStudy.jingjing2.domain.store.dto.request.StoreCreateRequest;
import com.UmcSpringStudy.jingjing2.domain.store.dto.response.StoreResponse;
import com.UmcSpringStudy.jingjing2.global.exception.CustomException;
import com.UmcSpringStudy.jingjing2.global.exception.errorcodes.CommonErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Transactional
    public StoreResponse createStore(StoreCreateRequest request) {
        Region region = regionRepository.findById(request.getRegionId())
                .orElseThrow(() -> new CustomException(CommonErrorCode.RESOURCE_NOT_FOUND));

        Store newStore = StoreConverter.toStore(request, region);
        return StoreConverter.toStoreResponse(storeRepository.save(newStore));
    }
}