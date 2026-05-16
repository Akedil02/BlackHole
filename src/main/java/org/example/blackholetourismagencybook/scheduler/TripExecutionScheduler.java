package org.example.blackholetourismagencybook.scheduler;

import org.example.blackholetourismagencybook.core.entity.BookingOrder;
import org.example.blackholetourismagencybook.core.repository.BookingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TripExecutionScheduler {

    @Autowired
    private BookingOrderRepository bookingOrderRepository;

    @Scheduled(fixedRate = 10000)
    public void simulateTripProgress(){
        List<BookingOrder> paidOrders = bookingOrderRepository.findByStatus(BookingOrder.OrderStatus.PAID);

        for (BookingOrder order : paidOrders) {
            order.setStatus(BookingOrder.OrderStatus.DEPARTED);
            System.out.println("🚀 Order [" + order.getId() + "] Spaceship has launched and cleared Earth's orbit!");
        }
        bookingOrderRepository.saveAll(paidOrders);

        // 2. DEPARTED -> IN_ORBIT (Spaceship arrives at the edge of the black hole's event horizon)
        List<BookingOrder> departedOrders = bookingOrderRepository.findByStatus(BookingOrder.OrderStatus.DEPARTED);
        for (BookingOrder order : departedOrders) {
            order.setStatus(BookingOrder.OrderStatus.IN_ORBIT);
            System.out.println("🌌 Order [" + order.getId() + "] Entered the black hole's gravitational field, time dilation accelerating sharply!");
        }
        bookingOrderRepository.saveAll(departedOrders);

        // 3. IN_ORBIT -> RETURNING (Sightseeing finished, initiating return trip)
        List<BookingOrder> inOrbitOrders = bookingOrderRepository.findByStatus(BookingOrder.OrderStatus.IN_ORBIT);
        for (BookingOrder order : inOrbitOrders) {
            order.setStatus(BookingOrder.OrderStatus.RETURNING);
            System.out.println("☄️ Order [" + order.getId() + "] Tour completed, engaging antimatter engines for return...");
        }
        bookingOrderRepository.saveAll(inOrbitOrders);

        // 4. RETURNING -> COMPLETED (Safely returned to Earth, entering the final settlement phase)
        List<BookingOrder> returningOrders = bookingOrderRepository.findByStatus(BookingOrder.OrderStatus.RETURNING);
        for (BookingOrder order : returningOrders) {
            order.setStatus(BookingOrder.OrderStatus.COMPLETED);
            System.out.println("🌍 Order [" + order.getId() + "] Spaceship landed on Earth. " + order.getExpectedEarthYears() + " years have passed on Earth.");
            // Trigger another Kafka event here: TripCompletedEvent, notifying the insurance/claims system for final reconciliation
        }
        bookingOrderRepository.saveAll(returningOrders);

    }
}
