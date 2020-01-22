package com.xpeppers;

import java.util.UUID;

public class PaymentService {
    private OrderRepository orderRepository;
    private PaymentRepository paymentRepository;

    public PaymentService(OrderRepository orderRepository, PaymentRepository paymentRepository) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
    }

    public Payment payOrderWith(UUID id) {
        Order order = orderRepository.findBy(id);

        Payment payment = pay(order);

        if (payment.isCompleted())
            orderRepository.update(order.paid());

        paymentRepository.add(payment);
        return payment;
    }

    private Payment pay(Order order) {
        UUID orderId = order.id();
        if (order.canBePaid()) {
            // payment logic (call external service like PayPal or other)
            sendEmail("user@wonderfuldomain.com", "your order for " + order.productCode() + " has been paid.");
            return Payment.completed(orderId);
        }

        return Payment.failed(orderId);
    }

    private void sendEmail(String to, String body) {
        System.out.println("---------------------------------------------------");
        System.out.println("From: xcommerce");
        System.out.println("To: " + to);
        System.out.println("Body: " + body);
        System.out.println("---------------------------------------------------");
    }
}
