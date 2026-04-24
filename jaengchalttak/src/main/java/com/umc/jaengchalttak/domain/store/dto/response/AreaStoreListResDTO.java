package com.umc.jaengchalttak.domain.store.dto.response;

import com.umc.jaengchalttak.domain.store.enums.StoreType;
import lombok.Builder;

@Builder
public record AreaStoreListResDTO(
        Long storeId,
        Integer missionPoint,
        String storeName,
        String storeAddress,
        StoreType storeType,
        String storeSavePath
) {}
