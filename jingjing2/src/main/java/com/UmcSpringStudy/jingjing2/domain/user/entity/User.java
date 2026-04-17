package com.UmcSpringStudy.jingjing2.domain.user.entity;

import com.UmcSpringStudy.jingjing2.domain.user.enums.Provider;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @Column(nullable = false, length = 100)
    private String sub;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private Provider provider;

    @Column(length = 1)
    private String sex;

    @Column(length = 20)
    private String username;

    private LocalDate birth;

    @Column(length = 100)
    private String address;

    @Column(length = 100)
    private String email;

    @Column(length = 13)
    private String phone;

    private Integer point;

    @Column(name = "CM_count")
    private Integer cmCount;

    private LocalDate created;

    @Column(name = "Field")
    private LocalDate field;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Setting setting;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Autorication autorication;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserInterest> userInterests = new ArrayList<>();
}