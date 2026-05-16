package org.example.blackholetourismagencybook.externalBankClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "mock-bank-client", url = "http://localhost:8080/api/external/mock-bank")
public interface PaymentFeignClient {
    @PostMapping("/charge")
    PaymentDTO.Response processCharge(@RequestBody PaymentDTO.Request request);

    @PostMapping("/refund")
    PaymentDTO.Response processRefound(@RequestBody PaymentDTO.Request request);
}
