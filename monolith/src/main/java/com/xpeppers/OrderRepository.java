package com.xpeppers;

import java.util.*;

public class OrderRepository {
    private static final Map<UUID, Order> orders = new HashMap<>();

    public void add(Order order) {
        orders.put(order.id(), order);
    }

    public void update(Order order) {
        orders.put(order.id(), order);
    }

    public List<Order> all() {
        return new ArrayList<>(orders.values());
    }

    public Order findBy(UUID id) {
        return orders.get(id);
    }
}