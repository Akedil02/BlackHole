package org.example.blackholetourismagencybook.core.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "booking_orders")
public class BookingOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long blackHoleId;

    @Column(nullable = false)
    private double orbitRadiusKm;

    @Column(nullable = false)
    private double expectedShipYears;

    @Column(nullable = false)
    private double expectedEarthYears;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status = OrderStatus.DRAFT;

    private LocalDateTime createdAt = LocalDateTime.now();

    private Double actualShipYears;
    private Double actualEarthYears;
    private Double discrepancyPercentage;
    private Integer compensationTier;


    public enum OrderStatus{
        DRAFT,
        PENDING_PAYMENT,
        PAID,
        CANCELED,

        //---Execution---//

        DEPARTED,
        IN_ORBIT,
        RETURNING,
        COMPLETED,
        DISPUTE_PENDING,
        REFUNDED,
        EXCHANGED
    }

}
