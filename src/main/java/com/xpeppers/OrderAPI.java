package com.xpeppers;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.util.UUID;

public class OrderAPI {

    private OrderService orderService;

    public OrderAPI(OrderService orderService) {
        this.orderService = orderService;
    }

    String create(Request request, Response response) {
        String productCode = request.queryParams("product_code");
        Integer productQuantity = Integer.valueOf(request.queryParams("product_quantity"));

        response.status(201);
        Order order = orderService.place(new Order(UUID.randomUUID(), productCode, productQuantity));
        return asJson(order);
    }

    private String asJson(Order order) {
        return (new Gson()).toJson(order);
    }

}
