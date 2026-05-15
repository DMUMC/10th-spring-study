package com.UmcSpringStudy.jingjing2.domain.store.converter;

import com.UmcSpringStudy.jingjing2.domain.store.entity.Region;
import com.UmcSpringStudy.jingjing2.domain.store.entity.Store;
import com.UmcSpringStudy.jingjing2.domain.store.dto.request.StoreCreateRequest;
import com.UmcSpringStudy.jingjing2.domain.store.dto.response.StoreResponse;

public class StoreConverter {

    /**
     * StoreCreateRequest -> Store 엔티티
     */
    public static Store toStore(StoreCreateRequest request, Region region) {
        return Store.builder()
                .name(request.getName())
                .category(request.getCategory())
                .rate(0.0) // 초기 평점 0.0
                .region(region)
                .build();
    }

    /**
     * Store 엔티티 -> StoreResponse (반환용 DTO)
     */
    public static StoreResponse toStoreResponse(Store store) {
        return StoreResponse.builder()
                .storeId(store.getId())
                .name(store.getName())
                .category(store.getCategory())
                .rate(store.getRate())
                // 객체 그래프 탐색: Store -> Region -> Name
                .region(store.getRegion() != null ? store.getRegion().getName() : null)
                .build();
    }
}