package com.umc.jaengchalttak.domain.store.dto.response;

import lombok.Builder;

@Builder
public record StoreInfoResDTO(
    String storeName,
    String storeType,
    String isOpen,
    Double storeStar,
    String storeAddress,
    String[] storeSavePath
) { }
