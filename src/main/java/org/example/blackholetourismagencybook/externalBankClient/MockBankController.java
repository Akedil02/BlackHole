package org.example.blackholetourismagencybook.externalBankClient;

import org.springframework.web.bind.annotation.*;
import java.util.UUID;


@RestController
@RequestMapping("/api/external/mock-bank")
public class MockBankController {

    @PostMapping("/charge")
    public PaymentDTO.Response charge(@RequestBody PaymentDTO.Request request) {
        System.out.println(" [Mock Bank] Received deduction request: User " + request.getUserId() + "，amount " + request.getAmount());
        PaymentDTO.Response response = new PaymentDTO.Response();
        response.setSuccess(true);
        response.setTransactionId("TX-CHARGE-" + UUID.randomUUID().toString().substring(0, 8));
        response.setMessage("Deduction succeed (Simulated)");
        return response;
    }


    @PostMapping("/refund")
    public PaymentDTO.Response refund(@RequestBody PaymentDTO.Request request) {
        System.out.println(" [Mock Bank] Received refund request: User " + request.getOrderId() + "，amount " + request.getAmount());
        PaymentDTO.Response response = new PaymentDTO.Response();
        response.setSuccess(true);
        response.setTransactionId("TX-REFUND-" + UUID.randomUUID().toString().substring(0, 8));
        response.setMessage("Refund succeed (Simulated)");
        return response;
    }
}