package org.example.blackholetourismagencybook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients
public class BlackHoleTourismAgencyBookApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlackHoleTourismAgencyBookApplication.class, args);
    }

}
