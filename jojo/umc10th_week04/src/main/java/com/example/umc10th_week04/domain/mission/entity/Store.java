package com.example.umc10th_week04.domain.mission.entity;

import com.example.umc10th_week04.domain.review.entity.Review;
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
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "location", nullable = false)
    private String location;

    @OneToMany(mappedBy = "store")
    private List<Mission> missionList = new ArrayList<>();

    @OneToMany(mappedBy = "store",  cascade = CascadeType.REMOVE)
    private List<Review> reviewList = new ArrayList<>();
}
