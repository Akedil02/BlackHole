package org.example.blackholetourismagencybook.core.controller;


import org.example.blackholetourismagencybook.auth.entity.User;
import org.example.blackholetourismagencybook.auth.repository.UserRepository;
import org.example.blackholetourismagencybook.core.dto.BookingRequestDTO;
import org.example.blackholetourismagencybook.core.entity.BookingOrder;
import org.example.blackholetourismagencybook.core.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/calculate")
    public ResponseEntity<?> calculateTrip(@RequestBody BookingRequestDTO request, Principal principal){
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(()-> new RuntimeException("User does not exist"));

        BookingOrder draftOrder = bookingService.calculateAndDraftBooking(user.getId(), request);
        return ResponseEntity.ok(draftOrder);
    }
}
