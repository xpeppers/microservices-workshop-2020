package com.xpeppers;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        Notifier notifier = new MicroserviceNotifier();
        OrderRepository orderRepository = new OrderRepository();

        OrderAPI orderAPI = orderAPI(orderRepository, notifier);

        port(8282);
        post("/orders", orderAPI::create);
        get("/orders", orderAPI::list);
        get("/orders/:id", orderAPI::findBy);
        post("/orders/:id/markAsPaid", orderAPI::markAsPaid);
    }

    private static OrderAPI orderAPI(OrderRepository orderRepository, Notifier notifier) {
        Warehouse warehouse = new Warehouse(notifier);
        OrderService orderService = new OrderService(orderRepository, warehouse);
        return new OrderAPI(orderService);
    }

}
