package org.example.blackholetourismagencybook.scheduler; // 保持你的包名

import org.example.blackholetourismagencybook.core.entity.BookingOrder;
import org.example.blackholetourismagencybook.core.repository.BookingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TripExecutionService {

    @Autowired
    private BookingOrderRepository bookingOrderRepository;

    @Transactional
    public void simulateTripProgress() {


        // RETURNING -> COMPLETED
        List<BookingOrder> returningOrders = bookingOrderRepository.findByStatus(BookingOrder.OrderStatus.RETURNING);
        for (BookingOrder order : returningOrders) {
            order.setStatus(BookingOrder.OrderStatus.COMPLETED);
            System.out.println(" Order [" + order.getId() + "] Spaceship landed on Earth. " + order.getExpectedEarthYears() + " years have passed on Earth.");
            // Trigger Kafka event...
        }
        bookingOrderRepository.saveAll(returningOrders);

        // IN_ORBIT -> RETURNING
        List<BookingOrder> inOrbitOrders = bookingOrderRepository.findByStatus(BookingOrder.OrderStatus.IN_ORBIT);
        for (BookingOrder order : inOrbitOrders) {
            order.setStatus(BookingOrder.OrderStatus.RETURNING);
            System.out.println(" Order [" + order.getId() + "] Tour completed, engaging antimatter engines for return...");
        }
        bookingOrderRepository.saveAll(inOrbitOrders);

        // DEPARTED -> IN_ORBIT
        List<BookingOrder> departedOrders = bookingOrderRepository.findByStatus(BookingOrder.OrderStatus.DEPARTED);
        for (BookingOrder order : departedOrders) {
            order.setStatus(BookingOrder.OrderStatus.IN_ORBIT);
            System.out.println(" Order [" + order.getId() + "] Entered the black hole's gravitational field, time dilation accelerating sharply!");
        }
        bookingOrderRepository.saveAll(departedOrders);

        // PAID -> DEPARTED
        List<BookingOrder> paidOrders = bookingOrderRepository.findByStatus(BookingOrder.OrderStatus.PAID);
        for (BookingOrder order : paidOrders) {
            order.setStatus(BookingOrder.OrderStatus.DEPARTED);
            System.out.println(" Order [" + order.getId() + "] Spaceship has launched and cleared Earth's orbit!");
        }
        bookingOrderRepository.saveAll(paidOrders);
    }
}