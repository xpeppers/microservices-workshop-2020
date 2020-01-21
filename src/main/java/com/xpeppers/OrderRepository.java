package com.xpeppers;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private static final List<Order> orders = new ArrayList<>();

    public void add(Order order) {
        orders.add(order);
    }
}