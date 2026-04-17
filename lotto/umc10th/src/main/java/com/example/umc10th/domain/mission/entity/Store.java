package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.member.enums.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    @Enumerated(EnumType.STRING)
    private Address address;

    @Column(name = "detail_address")
    private String detailAddress;

    @Column(name = "information")
    private String information;


}
