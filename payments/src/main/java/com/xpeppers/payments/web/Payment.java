package com.xpeppers.payments.web;

import java.util.UUID;

public class Payment {
    private UUID id;

    private UUID orderId;

    private String status;
    public Payment(UUID id, UUID orderId, String status) {
        this.id = id;
        this.orderId = orderId;
        this.status = status;
    }

    public static Payment completed(UUID orderId) {
        return new Payment(UUID.randomUUID(), orderId, "completed");
    }

    public static Payment failed(UUID orderId) {
        return new Payment(UUID.randomUUID(), orderId, "failed");
    }

    public UUID getId() {
        return id;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public String getStatus() {
        return status;
    }

    public boolean isCompleted() {
        return "completed".equals(status);
    }
}
