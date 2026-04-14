package com.umc.jaengchalttak.domain.store.dto.response;

public record AreaStoreListResponseDTO(
        Long storeId,
        Integer missionPoint,
        String storeName,
        String storeAddress,
        String storeType,
        String storeSavePath
) {}
