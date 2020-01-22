package com.xpeppers;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.util.UUID;

class PaymentAPI {
    private PaymentService paymentService;

    PaymentAPI(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    String create(Request request, Response response) {
        UUID orderId = UUID.fromString(request.queryParams("order_id"));

        Payment payment = paymentService.payOrderWith(orderId);

        if(payment.isCompleted()) {
            response.status(201);
            return asJson(payment);
        }

        response.status(406);
        return asJson(payment);
    }

    private String asJson(Object object) {
        return (new Gson()).toJson(object);
    }
}