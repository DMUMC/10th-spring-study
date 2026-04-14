package com.umc.jaengchalttak.domain.store.dto.response;

public record StoreInfoResponseDTO(
    String storeName,
    String storeType,
    String isOpen,
    Double storeStar,
    String storeAddress,
    String[] storeSavePath
) { }
