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

    private MeterRegistry meterRegistry;
    private Counter successfulPayments;
    private Counter failedPayments;

    public PaymentService(OrderRepository orderRepository, Notifier notifier, MeterRegistry meterRegistry) {
        this.orderRepository = orderRepository;
        this.notifier = notifier;
        this.meterRegistry = meterRegistry;

        this.successfulPayments = this.meterRegistry.counter("payments", "type", "successful");
        this.failedPayments = this.meterRegistry.counter("payments", "type", "failed");
    }

    public Payment payOrderWith(UUID id) {
        Order order = orderRepository.findBy(id);

        Payment payment = pay(order);

        if (payment.isCompleted()) {
            orderRepository.markAsPaid(order);
            successfulPayments.increment();
        } else {
            failedPayments.increment();
        }

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
