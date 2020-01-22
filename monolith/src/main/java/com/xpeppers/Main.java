package com.xpeppers;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        OrderRepository orderRepository = new OrderRepository();
        OrderAPI orderAPI = orderAPI(orderRepository);
        PaymentAPI paymentAPI = paymentAPI(orderRepository);

        port(8282);
        post("/orders", orderAPI::create);
        get("/orders", orderAPI::list);
        post("/payments", paymentAPI::create);
    }

    private static PaymentAPI paymentAPI(OrderRepository orderRepository) {
        PaymentRepository paymentRepository = new PaymentRepository();
        PaymentService paymentService = new PaymentService(orderRepository, paymentRepository);
        return new PaymentAPI(paymentService);
    }

    private static OrderAPI orderAPI(OrderRepository orderRepository) {
        Warehouse warehouse = new Warehouse();
        OrderService orderService = new OrderService(orderRepository, warehouse);
        return new OrderAPI(orderService);
    }

}
