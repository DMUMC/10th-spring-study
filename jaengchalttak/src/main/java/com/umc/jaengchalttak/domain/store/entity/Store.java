package com.umc.jaengchalttak.domain.store.entity;

import com.umc.jaengchalttak.domain.mission.entity.Mission;
import com.umc.jaengchalttak.domain.store.enums.StoreType;
import com.umc.jaengchalttak.domain.user.enums.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "store")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String storeName;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private StoreType storeType;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean isOpen;

    @DecimalMin(value = "1.0")
    @DecimalMax(value = "5.0")
    @Column(nullable = false)
    private Double storeStar;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Address storeAddress;

    // ====== 연관관계 매핑 ======
    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private List<Mission> missionList = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private List<StorePhoto> storePhotos = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private List<StoreReview> storeReviews = new ArrayList<>();

}