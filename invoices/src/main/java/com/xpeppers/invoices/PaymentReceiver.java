package com.xpeppers.invoices;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "hello")
public class PaymentReceiver {

    @RabbitHandler
    public void receive(String payment) {
        System.out.println("Preparing invoice for '" + payment + "'");
    }
}