package com.umc.jaengchalttak.domain.store.dto.response;

import lombok.Builder;

@Builder
public record GetStoreMissionResDTO(
        Long missionId,
        Integer missionAmount,
        Integer missionPoint
) { }
