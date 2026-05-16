package org.example.blackholetourismagencybook.core.dto;

import lombok.Data;

@Data
public class DisputeResolutionDTO {
    public enum ResolutionChoice {REFUND, EXCHANGE}
    private ResolutionChoice choice;
}
