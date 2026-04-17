package com.umc.jaengchalttak.domain.store.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record StoreInfoResDTO(
    String storeName,
    String storeType,
    String isOpen,
    Double storeStar,
    String storeAddress,
    List<String> storeSavePath
) { }
