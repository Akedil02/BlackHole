package org.example.blackholetourismagencybook.externalBankClient;

import lombok.Data;

public class PaymentDTO {

    @Data
    public static class Request {
        private Long userId;
        private Long orderId;
        private Double amount;
    }

    @Data
    public static class Response {
        private boolean success;
        private String transactionId;
        private String message;
    }
}
