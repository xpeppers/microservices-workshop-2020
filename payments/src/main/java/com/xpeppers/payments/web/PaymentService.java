package com.xpeppers.payments.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentService {

    @Autowired
    private final Notifier notifier;

    @Autowired
    private OrderRepository orderRepository;

    public PaymentService(OrderRepository orderRepository, Notifier notifier) {
        this.orderRepository = orderRepository;
        this.notifier = notifier;
    }

    public Payment payOrderWith(UUID id) {
        Order order = orderRepository.findBy(id);

        Payment payment = pay(order);

        if (payment.isCompleted())
            orderRepository.markAsPaid(order);

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
