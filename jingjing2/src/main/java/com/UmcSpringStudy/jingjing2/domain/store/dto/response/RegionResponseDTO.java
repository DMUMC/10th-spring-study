package com.UmcSpringStudy.jingjing2.domain.store.dto.response;

import lombok.Builder;
import lombok.Getter;

public class RegionResponseDTO {

    @Builder
    @Getter
    public static class RegionPreviewDTO {
        private Long id;
        private String name;
    }

    @Builder
    @Getter
    public static class RegionListDTO {
        private java.util.List<RegionPreviewDTO> regionList;
        private Boolean hasNext;
    }
}