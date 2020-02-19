package com.xpeppers.payments.web;

import com.google.gson.Gson;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PaymentPublisher {

    @Autowired
    private RabbitTemplate template;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void publish(Payment payment) {
        this.template.convertAndSend("hello", asJson(payment));
    }

    private String asJson(Payment payment) {
        return (new Gson()).toJson(payment);
    }
}

