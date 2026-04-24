package com.umc.jaengchalttak.domain.store.dto.response;

import com.umc.jaengchalttak.domain.store.enums.StoreType;
import lombok.Builder;

import java.util.List;

@Builder
public record StoreInfoResDTO(
    String storeName,
    StoreType storeType,
    String isOpen,
    Double storeStar,
    String storeAddress,
    List<String> storeSavePath
) { }
