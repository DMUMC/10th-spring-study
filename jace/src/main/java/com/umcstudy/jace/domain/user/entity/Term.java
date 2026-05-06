package com.umcstudy.jace.domain.user.entity;

import com.umcstudy.jace.domain.user.entity.mapping.UserTerm;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "terms")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "terms_id")
    private Long id;

    @Column(name = "terms_title", nullable = false, length = 200)
    private String termsTitle;

    @Column(name = "terms_content", nullable = false, columnDefinition = "TEXT")
    private String termsContent;

    @Column(name = "is_required", nullable = false)
    private Boolean isRequired;

    @Column(name = "terms_version", nullable = false, length = 20)
    private String termsVersion;

    @Column(name = "terms_create_date", nullable = false)
    private LocalDateTime termsCreateDate;

    @Column(name = "terms_updated_date")
    private LocalDateTime termsUpdatedDate;

    @Builder.Default
    @OneToMany(mappedBy = "term")
    private List<UserTerm> userTerms = new ArrayList<>();
}
