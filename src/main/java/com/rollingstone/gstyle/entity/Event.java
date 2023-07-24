package com.rollingstone.gstyle.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "survey_event")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long stuno;

    @Column
    private int classcount;
}
