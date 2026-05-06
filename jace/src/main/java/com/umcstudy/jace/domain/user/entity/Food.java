package com.umcstudy.jace.domain.user.entity;

import com.umcstudy.jace.domain.user.entity.mapping.UserFood;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "food")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private Long id;

    @Column(name = "food_name", nullable = false, length = 20)
    private String foodName;

    @Builder.Default
    @OneToMany(mappedBy = "food")
    private List<UserFood> userFoods = new ArrayList<>();
}
