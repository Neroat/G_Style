package com.rollingstone.gstyle.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long stuno;

    //@Convert(converter = StringListConverter.class)
    @Column(nullable = false)
    private String detailSurvey;

    @Column(nullable = false)
    private String erating;

    private String createdAt;
}
