package org.example.blackholetourismagencybook.core.repository;

import org.example.blackholetourismagencybook.core.entity.BookingOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingOrderRepository extends JpaRepository<BookingOrder, Long> {
}
