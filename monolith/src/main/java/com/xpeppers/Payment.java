package com.xpeppers;

import java.util.UUID;

public class Payment {
    private final UUID id;
    private final UUID orderId;
    private final String status;

    private Payment(UUID id, UUID orderId, String status) {
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

    public UUID id() {
        return id;
    }

    public UUID orderId() {
        return orderId;
    }

    public boolean isCompleted() {
        return "completed".equals(status);
    }

    public boolean isFailed() {
        return ! isCompleted();
    }
}
