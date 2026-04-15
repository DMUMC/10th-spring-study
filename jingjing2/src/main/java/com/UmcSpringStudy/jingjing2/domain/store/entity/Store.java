package com.UmcSpringStudy.jingjing2.domain.store.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
@lombok.Data
@Entity
public class Store {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal lat;
    private BigDecimal lng;
    private Double rate;
    private String category;
}