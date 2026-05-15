package com.UmcSpringStudy.jingjing2.domain.store.converter;

import com.UmcSpringStudy.jingjing2.domain.store.entity.Region;
import com.UmcSpringStudy.jingjing2.domain.store.dto.request.RegionRequestDTO;
import com.UmcSpringStudy.jingjing2.domain.store.dto.response.RegionResponseDTO;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.stream.Collectors;

public class RegionConverter {

    /**
     * 1. RegionRequestDTO -> Region 엔티티 변환
     */
    public static Region toRegion(RegionRequestDTO request) {
        return Region.builder()
                .name(request.getName())
                .build();
    }

    /**
     * 2. Slice<Region> -> RegionListDTO 변환 (페이징 및 검색 결과용)
     * Slice 객체로부터 현재 페이지의 데이터 리스트와 다음 페이지 존재 여부를 추출합니다.
     */
    public static RegionResponseDTO.RegionListDTO toRegionListDTO(Slice<Region> regionSlice) {
        List<RegionResponseDTO.RegionPreviewDTO> previewDTOList = regionSlice.stream()
                .map(RegionConverter::toRegionPreviewDTO)
                .collect(Collectors.toList());

        return RegionResponseDTO.RegionListDTO.builder()
                .regionList(previewDTOList)
                .hasNext(regionSlice.hasNext()) // Slice의 hasNext()로 더보기 버튼 활성화 제어
                .build();
    }

    /**
     * 3. Region 엔티티 -> RegionPreviewDTO 변환
     */
    public static RegionResponseDTO.RegionPreviewDTO toRegionPreviewDTO(Region region) {
        return RegionResponseDTO.RegionPreviewDTO.builder()
                .id(region.getId())
                .name(region.getName())
                .build();
    }
}