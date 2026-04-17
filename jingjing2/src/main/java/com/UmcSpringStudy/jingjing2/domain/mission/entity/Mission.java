package com.UmcSpringStudy.jingjing2.domain.mission.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
@lombok.Data
@Entity
public class Mission {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    private String name;
    private String context;
    private Double rate;
    private Integer taskCount;
    private String reward;
    private LocalDate expDate;
    @Column(name = "store_id", nullable = false)
    private Long storeId;
}