package com.example.umc10th_week04.domain.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    // 연관 관계
    @Builder.Default
    @OneToMany(mappedBy = "food", cascade = CascadeType.REMOVE)
    private List<UserFood> userFoodList = new ArrayList<>();
}
