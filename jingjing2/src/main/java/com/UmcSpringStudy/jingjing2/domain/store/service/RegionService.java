package com.UmcSpringStudy.jingjing2.domain.store.service;

import com.UmcSpringStudy.jingjing2.domain.store.converter.RegionConverter;
import com.UmcSpringStudy.jingjing2.domain.store.entity.Region;
import com.UmcSpringStudy.jingjing2.domain.store.repository.RegionRepository;
import com.UmcSpringStudy.jingjing2.domain.store.dto.request.RegionRequestDTO;
import com.UmcSpringStudy.jingjing2.domain.store.dto.response.RegionResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RegionService {

    private final RegionRepository regionRepository;

    public RegionResponseDTO.RegionListDTO getRegionList(String keyword, Integer page) {
        Slice<Region> regionSlice = regionRepository.findByNameContaining(keyword, PageRequest.of(page, 20));
        return RegionConverter.toRegionListDTO(regionSlice);
    }

    @Transactional
    public Long createRegion(RegionRequestDTO request) {
        Region region = RegionConverter.toRegion(request);
        return regionRepository.save(region).getId();
    }
}