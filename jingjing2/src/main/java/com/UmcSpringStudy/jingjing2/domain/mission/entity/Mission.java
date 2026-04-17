package com.UmcSpringStudy.jingjing2.domain.mission.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
@lombok.Data
@Entity
public class Mission {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long msid;

    private String msname;
    private String context;
    private Double msrate;
    private Integer taskCount;
    private String reward;
    private LocalDate expDate;
}