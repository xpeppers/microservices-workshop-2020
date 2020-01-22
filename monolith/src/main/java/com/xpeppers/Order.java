package com.xpeppers;

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

    public Order(UUID uuid, String productCode, Integer productQuantity) {
        this(uuid, productCode, productQuantity, "created");
    }

    public Order reserved() {
        return new Order(uuid, productCode, productQuantity, "reserved");
    }

    public Order placed() {
        return new Order(uuid, productCode, productQuantity, "placed");
    }

    public Order paid() {
        return new Order(uuid, productCode, productQuantity, "paid");
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
