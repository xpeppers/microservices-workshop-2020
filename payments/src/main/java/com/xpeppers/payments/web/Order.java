package com.xpeppers.payments.web;

import java.util.UUID;

public class Order {
    private final UUID uuid;
    private final String productCode;
    private final Integer productQuantity;
    private final String status;

    private Order(UUID uuid, String productCode, Integer productQuantity, String status) {
        this.uuid = uuid;
        this.productCode = productCode;
        this.productQuantity = productQuantity;
        this.status = status;
    }

    public UUID id() {
        return uuid;
    }

    public String productCode() {
        return productCode;
    }

    public Integer productQuantity() {
        return productQuantity;
    }

    public boolean canBePaid() {
        return "placed".equals(status) || "reserved".equals(status);
    }
}
