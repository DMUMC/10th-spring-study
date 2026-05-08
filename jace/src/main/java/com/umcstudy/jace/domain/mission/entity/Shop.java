package com.umcstudy.jace.domain.mission.entity;

import com.umcstudy.jace.domain.mission.entity.mapping.ShopCategory;
import com.umcstudy.jace.domain.mission.entity.mapping.ShopImage;
import com.umcstudy.jace.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shop")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private Long id;

    @Column(name = "shop_name", nullable = false, length = 100)
    private String shopName;

    @Column(name = "shop_open_time", nullable = false)
    private LocalTime shopOpenTime;

    @Column(name = "shop_close_time", nullable = false)
    private LocalTime shopCloseTime;

    @Column(name = "shop_address", nullable = false)
    private String shopAddress;

    @Column(name = "shop_score", nullable = false, precision = 2, scale = 1)
    private BigDecimal shopScore;

    @Column(nullable = false, precision = 10, scale = 7)
    private BigDecimal latitude;

    @Column(nullable = false, precision = 10, scale = 7)
    private BigDecimal longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_category_id", nullable = false)
    private ShopCategory shopCategory;

    @Builder.Default
    @OneToMany(mappedBy = "shop")
    private List<Mission> missions = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "shop")
    private List<ShopImage> shopImages = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "shop")
    private List<Review> reviews = new ArrayList<>();
}
