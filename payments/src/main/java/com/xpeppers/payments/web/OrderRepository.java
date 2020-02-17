package com.xpeppers.payments.web;

import org.springframework.stereotype.Component;

import java.util.UUID;

import static kong.unirest.Unirest.get;
import static kong.unirest.Unirest.post;

@Component
public class OrderRepository {
    public Order findBy(UUID id) {
        return get("http://proxy/orders/" + id)
                .asObject(Order.class).getBody();
    }

    public void markAsPaid(Order order) {
        post("http://proxy/orders/" + order.id() + "/markAsPaid").asBytes();
    }
}