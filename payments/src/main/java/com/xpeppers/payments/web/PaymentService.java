package com.xpeppers.payments.web;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentService {

    @Autowired
    private final Notifier notifier;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    PaymentPublisher paymentPublisher;

    @Autowired
    PaymentInstrumentation instrumentation;

    public PaymentService(OrderRepository orderRepository, Notifier notifier) {
        this.orderRepository = orderRepository;
        this.notifier = notifier;
    }

    public Payment payOrderWith(UUID id) {
        Order order = orderRepository.findBy(id);

        Payment payment = pay(order);

        if (payment.isCompleted())
            orderRepository.markAsPaid(order);

        instrumentation.paymentProcessed(payment);

        paymentPublisher.publish(payment);

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
