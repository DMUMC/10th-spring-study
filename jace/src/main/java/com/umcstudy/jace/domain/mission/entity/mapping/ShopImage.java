package com.umcstudy.jace.domain.mission.entity.mapping;

import com.umcstudy.jace.domain.mission.entity.Shop;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "shop_image")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ShopImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_image_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    @Column(name = "shop_image_url", nullable = false, length = 500)
    private String shopImageUrl;
}
