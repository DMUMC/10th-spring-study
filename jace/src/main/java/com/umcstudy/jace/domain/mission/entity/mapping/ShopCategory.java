package com.umcstudy.jace.domain.mission.entity.mapping;

import com.umcstudy.jace.domain.mission.entity.Shop;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shop_category")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ShopCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_category_id")
    private Long id;

    @Column(name = "shop_category_name", nullable = false, length = 30)
    private String shopCategoryName;

    @Builder.Default
    @OneToMany(mappedBy = "shopCategory")
    private List<Shop> shops = new ArrayList<>();
}
