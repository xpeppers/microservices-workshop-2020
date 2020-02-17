package com.xpeppers;

import java.util.UUID;

public class PaymentService {
    private final Notifier notifier;
    private OrderRepository orderRepository;

    public PaymentService(OrderRepository orderRepository, Notifier notifier) {
        this.orderRepository = orderRepository;
        this.notifier = notifier;
    }

    public Payment payOrderWith(UUID id) {
        Order order = orderRepository.findBy(id);

        Payment payment = pay(order);

        if (payment.isCompleted())
            orderRepository.update(order.paid());

        return payment;
    }

    private Payment pay(Order order) {
        UUID orderId = order.id();
        if (order.canBePaid()) {
            // payment logic (call external service like PayPal or other)
            notifier.sendEmail("user@wonderfuldomain.com", "your order for " + order.productCode() + " has been paid.");
            return Payment.completed(orderId);
        }

        return Payment.failed(orderId);
    }
}
