package com.umc.jaengchalttak.domain.store.dto.response;

import lombok.Builder;

@Builder
public record AreaStoreListResDTO(
        Long storeId,
        Integer missionPoint,
        String storeName,
        String storeAddress,
        String storeType,
        String storeSavePath
) {}
