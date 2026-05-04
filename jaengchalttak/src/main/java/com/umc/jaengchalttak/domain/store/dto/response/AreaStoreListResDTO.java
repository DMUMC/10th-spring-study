package com.umc.jaengchalttak.domain.store.dto.response;

import com.umc.jaengchalttak.domain.store.enums.StoreType;
import com.umc.jaengchalttak.domain.user.enums.Address;
import lombok.Builder;

@Builder
public record AreaStoreListResDTO(
        Long storeId,
        Integer missionPoint,
        String storeName,
        Address storeAddress,
        StoreType storeType,
        String storeSavePath
) {}
