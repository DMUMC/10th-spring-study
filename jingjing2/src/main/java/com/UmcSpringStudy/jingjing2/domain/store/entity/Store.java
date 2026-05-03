package com.UmcSpringStudy.jingjing2.domain.store.entity;

import com.UmcSpringStudy.jingjing2.domain.mission.entity.Mission;
import com.UmcSpringStudy.jingjing2.domain.review.entity.StoreReview;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    private String name;
    private BigDecimal lat;
    private BigDecimal lng;
    private Double rate;
    private String category;

    //구역과 가게 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    // 가게 이미지와의 양방향 연결
    @Builder.Default
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<StoreImage> storeImages = new ArrayList<>();

    // 가게 리뷰와의 양방향 연결
    @Builder.Default
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<StoreReview> reviews = new ArrayList<>();

    // 해당 가게의 미션과의 양방향 연결
    @Builder.Default
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Mission> missions = new ArrayList<>();
}