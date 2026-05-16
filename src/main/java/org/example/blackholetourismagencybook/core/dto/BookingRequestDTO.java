package org.example.blackholetourismagencybook.core.dto;

import lombok.Data;

@Data
public class BookingRequestDTO {
    private Long blackHoleId;      // destination id
    private double shipYears;      // how long does client plan to be on ship
    private OrbitType orbitType;

    public enum OrbitType {
        CONSERVATIVE, // conservatively sightseeing (far from horizon)
        EXTREME       // extreme experience(close)
    }
}