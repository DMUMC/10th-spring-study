package com.UmcSpringStudy.jingjing2.domain.review.entity;

import com.UmcSpringStudy.jingjing2.domain.store.entity.Store;
import com.UmcSpringStudy.jingjing2.domain.user.entity.User;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

@Entity
@Getter // @Data 대신 가급적 @Getter 사용 권장 (Setter 남용 방지)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA를 위한 기본 생성자
@AllArgsConstructor
public class StoreReview {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    private String title;
    private String context;
    private Double rate;
    private Integer popularity;
    private LocalDate createdDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
}
