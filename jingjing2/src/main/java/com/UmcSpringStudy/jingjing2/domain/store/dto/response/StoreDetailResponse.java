package com.UmcSpringStudy.jingjing2.domain.store.dto.response;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreDetailResponse {
    private Long storeId;
    private String name;
    private String category;
    private BigDecimal lat;
    private BigDecimal lng;
    private Double rate;
}