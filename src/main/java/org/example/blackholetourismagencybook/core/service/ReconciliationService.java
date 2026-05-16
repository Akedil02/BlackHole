package org.example.blackholetourismagencybook.core.service;

import jakarta.transaction.Transactional;
import org.example.blackholetourismagencybook.core.dto.DisputeResolutionDTO;
import org.example.blackholetourismagencybook.core.dto.TripTelemetryDTO;
import org.example.blackholetourismagencybook.core.entity.BookingOrder;
import org.example.blackholetourismagencybook.core.repository.BookingOrderRepository;
import org.example.blackholetourismagencybook.externalBankClient.PaymentDTO;
import org.example.blackholetourismagencybook.externalBankClient.PaymentFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;

@Service
public class ReconciliationService {
    @Autowired
    private BookingOrderRepository bookingOrderRepository;

    private static final double TOLERANCE_THRESHOLD = 0.02;
    @Autowired
    private PaymentFeignClient paymentFeignClient;

    @Transactional
    public BookingOrder processTelemetry(Long orderId, TripTelemetryDTO telemetry){
        BookingOrder order = bookingOrderRepository.findById(orderId)
                .orElseThrow(()-> new RuntimeException("Order doesn't exist"));

        //record the actual time from the black box
        order.setActualEarthYears(telemetry.getActualEarthYears());
        order.setActualShipYears(telemetry.getActualShipYears());

        //calculate the tolerance
        double expected = order.getExpectedEarthYears();
        double actual = telemetry.getActualEarthYears();
        double discrepancy = Math.abs(actual - expected) / expected;
        order.setDiscrepancyPercentage(discrepancy);

        if(discrepancy <= TOLERANCE_THRESHOLD){
            order.setStatus(BookingOrder.OrderStatus.COMPLETED);
            order.setCompensationTier(0);
        } else {
            order.setStatus(BookingOrder.OrderStatus.DISPUTE_PENDING);
            if(discrepancy <= 0.10){
                order.setCompensationTier(1);
            } else if (discrepancy <= 0.50) {
                order.setCompensationTier(2);
            } else{
                order.setCompensationTier(3);
            }
        }

        return bookingOrderRepository.save(order);
    }

    @Transactional
    public BookingOrder resolveDispute(Long userId, Long orderId, DisputeResolutionDTO resolution){
        BookingOrder order = bookingOrderRepository.findById(orderId)
                .orElseThrow(()-> new RuntimeException("Order doesn't exist"));

        if(!order.getUserId().equals(userId) || order.getStatus() != BookingOrder.OrderStatus.DISPUTE_PENDING){
            throw new IllegalArgumentException("Illegal operation, no permission to dispute or incorrect order status");
        }

        if(resolution.getChoice() == DisputeResolutionDTO.ResolutionChoice.REFUND){

            PaymentDTO.Request refundRequest = new PaymentDTO.Request();
            refundRequest.setUserId(userId);
            refundRequest.setOrderId(orderId);

            refundRequest.setAmount(500000.0);

            PaymentDTO.Response bankResponse = paymentFeignClient.processRefound(refundRequest);

            if(!bankResponse.isSuccess()){
                throw new RuntimeException("Refund transfer failed: "+ bankResponse.getMessage());
            }
            System.out.println("Refund successfully transferred. Transaction Id: " + bankResponse.getTransactionId());

            order.setStatus(BookingOrder.OrderStatus.REFUNDED);
        } else if (resolution.getChoice() == DisputeResolutionDTO.ResolutionChoice.EXCHANGE) {
            //Generate new draft order
        }

        return bookingOrderRepository.save(order);
    }

}
