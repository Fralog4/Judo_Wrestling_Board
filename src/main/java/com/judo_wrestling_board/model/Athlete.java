package com.judo_wrestling_board.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "athletes")
public class Athlete {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private String surname;
    private Sport sport;
    private WeightClass weightClass;
}
