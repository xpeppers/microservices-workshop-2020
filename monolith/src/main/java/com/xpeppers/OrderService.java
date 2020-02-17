package com.xpeppers;

import java.util.List;
import java.util.UUID;

public class OrderService {
    private OrderRepository orderRepository;
    private Warehouse warehouse;

    public OrderService(OrderRepository orderRepository, Warehouse warehouse) {
        this.orderRepository = orderRepository;
        this.warehouse = warehouse;
    }

    public Order place(Order order) {
        Order handledOrder = warehouse.reserveProductsFor(order);
        orderRepository.add(handledOrder);

        return handledOrder;
    }

    public List<Order> orders() {
        return orderRepository.all();
    }

    public Order findBy(UUID id) {
        return orderRepository.findBy(id);
    }

    public Order markAsPaid(Order order) {
        Order updatedOrder = order.paid();
        orderRepository.update(updatedOrder);
        return updatedOrder;
    }
}
