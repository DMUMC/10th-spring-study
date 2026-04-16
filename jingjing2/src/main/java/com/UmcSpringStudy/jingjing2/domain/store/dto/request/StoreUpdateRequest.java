package com.UmcSpringStudy.jingjing2.domain.store.dto.request;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreUpdateRequest {
    private String name;
    private String category;
    private BigDecimal lat;
    private BigDecimal lng;
    private Double rate;
}