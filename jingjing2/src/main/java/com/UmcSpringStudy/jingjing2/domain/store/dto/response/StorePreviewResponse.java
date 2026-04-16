package com.UmcSpringStudy.jingjing2.domain.store.dto.response;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StorePreviewResponse {
    private Long storeId;
    private String name;
    private String category;
    private Double rate;
}