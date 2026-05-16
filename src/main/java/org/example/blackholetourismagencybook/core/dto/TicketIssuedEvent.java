package org.example.blackholetourismagencybook.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketIssuedEvent implements Serializable {
    private Long orderId;
    private Long userId;
    private Long blackHoleId;
    private double expectedEarthYears;
}
