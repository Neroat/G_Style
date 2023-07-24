package com.rollingstone.gstyle.entity;

import com.rollingstone.gstyle.config.StringListConverter;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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
