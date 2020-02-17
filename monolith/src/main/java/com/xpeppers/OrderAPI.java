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

    String list(Request request, Response response) {
        return asJson(orderService.orders());
    }

    public String findBy(Request request, Response response) {
        UUID orderId = UUID.fromString(request.params(":id"));
        try {
            return asJson(orderService.findBy(orderId));
        } catch (Exception e) {
            response.status(404);
            return "";
        }
    }

    public String markAsPaid(Request request, Response response) {
        UUID orderId = UUID.fromString(request.params(":id"));
        try {
            Order order = orderService.findBy(orderId);
            Order paidOrder = orderService.markAsPaid(order);
            return asJson(paidOrder);
        } catch (Exception e) {
            response.status(404);
            return "";
        }
    }

    private String asJson(Object object) {
        return (new Gson()).toJson(object);
    }
}
