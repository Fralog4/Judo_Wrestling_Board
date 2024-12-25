package com.judo_wrestling_board.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "athlete")
public class Athlete {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private String surname;
    @Enumerated(EnumType.STRING)
    private Sport sport;
    @Enumerated(EnumType.STRING)
    private WeightClass weightClass;
}
