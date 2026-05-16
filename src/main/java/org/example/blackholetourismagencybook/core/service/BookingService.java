package org.example.blackholetourismagencybook.core.service;

import jakarta.transaction.Transactional;
import org.example.blackholetourismagencybook.core.dto.BookingRequestDTO;
import org.example.blackholetourismagencybook.core.dto.TicketIssuedEvent;
import org.example.blackholetourismagencybook.core.dto.WainverDecisionDTO;
import org.example.blackholetourismagencybook.core.entity.BlackHole;
import org.example.blackholetourismagencybook.core.entity.BookingOrder;
import org.example.blackholetourismagencybook.core.repository.BlackHoleRepository;
import org.example.blackholetourismagencybook.core.repository.BookingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import static org.example.blackholetourismagencybook.core.config.KafkaConfig.TOPIC_TICKET_ISSUED;

@Service
public class BookingService {
    @Autowired
    private PhysicsEngineService physicsEngineService;
    @Autowired
    private BookingOrderRepository bookingOrderRepository;
    @Autowired
    private BlackHoleRepository blackHoleRepository;

    @Transactional
    public BookingOrder calculateAndDraftBooking(Long userId, BookingRequestDTO request) {


        BlackHole blackHole = blackHoleRepository.findById(request.getBlackHoleId())
                .orElseThrow(() -> new RuntimeException("Destination doesn't exist or no more supported."));


        double rs = 2.95 * blackHole.getMassSolar();
        double orbitRadiusKm;

        if (request.getOrbitType() == BookingRequestDTO.OrbitType.EXTREME) {
            orbitRadiusKm = rs * 1.005;
        } else {
            orbitRadiusKm = rs * 3.0;
        }

        double earthYears = physicsEngineService.calculateEarthTime(request.getShipYears(), blackHole.getMassSolar(), orbitRadiusKm);

        BookingOrder order = new BookingOrder();
        order.setUserId(userId);
        order.setBlackHoleId(blackHole.getId());
        order.setOrbitRadiusKm(orbitRadiusKm);
        order.setExpectedShipYears(request.getShipYears());
        order.setExpectedEarthYears(earthYears);
        order.setStatus(BookingOrder.OrderStatus.DRAFT);

        return bookingOrderRepository.save(order);
    }

    @Transactional
    public BookingOrder processWaiverDecision(Long userId, Long orderId, WainverDecisionDTO decision){
        BookingOrder order = bookingOrderRepository.findById(orderId)
                .orElseThrow(()-> new RuntimeException("Order doesn't exist"));

        if(!order.getUserId().equals(userId) || order.getStatus() != BookingOrder.OrderStatus.DRAFT){
            throw new IllegalArgumentException("Illegal operation, order status is incorrect or permission denied");
        }

        if(!decision.isAccepted()){
            order.setStatus(BookingOrder.OrderStatus.CANCELED);
        } else {
            order.setStatus(BookingOrder.OrderStatus.PENDING_PAYMENT);
        }

        return bookingOrderRepository.save(order);
    }

    @Transactional
    public BookingOrder processPayment(Long userId, Long orderId){
        BookingOrder order = bookingOrderRepository.findById(orderId)
                .orElseThrow(()-> new RuntimeException("Order doesn't exist"));

        //paymentGateway.charge(userId, amount);

        order.setStatus(BookingOrder.OrderStatus.PAID);
        bookingOrderRepository.save(order);

        TicketIssuedEvent event = new TicketIssuedEvent(
                order.getId(),
                order.getUserId(),
                order.getBlackHoleId(),
                order.getExpectedEarthYears()
        );

        kafkaTemplate.send(TOPIC_TICKET_ISSUED, event);

        return order;


    }
}
