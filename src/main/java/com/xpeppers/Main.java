package com.xpeppers;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();
        OrderRepository orderRepository = new OrderRepository();
        OrderService orderService = new OrderService(orderRepository, warehouse);
        OrderAPI orderAPI = new OrderAPI(orderService);

        port(8282);
        post("/orders", orderAPI::create);
        get("/orders", orderAPI::list);
    }

}
