package com.umc.jaengchalttak.domain.store.dto.response;

import com.umc.jaengchalttak.domain.store.enums.StoreType;
import com.umc.jaengchalttak.domain.user.enums.Address;
import lombok.Builder;

import java.util.List;

@Builder
public record StoreInfoResDTO(
    String storeName,
    StoreType storeType,
    String isOpen,
    Double storeStar,
    Address storeAddress,
    List<String> storeSavePath
) { }
