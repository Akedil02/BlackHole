package org.example.blackholetourismagencybook.scheduler;

import org.example.blackholetourismagencybook.scheduler.TripExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/trips")
public class TripAdminController {

    @Autowired
    private TripExecutionService tripExecutionService;

    @PostMapping("/advance-time")
    public String advanceTime() {
        tripExecutionService.simulateTripProgress();
        return "Time advanced successfully. Check backend console for spaceship updates.";
    }
}