package org.example.blackholetourismagencybook.core.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "black_holes")
public class BlackHole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String constellation;
    private double distanceLy;
    private double massSolar;
    private String description;
    private double timeDilationFactor;
}
