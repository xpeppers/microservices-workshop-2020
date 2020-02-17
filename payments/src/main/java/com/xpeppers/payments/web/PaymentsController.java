package com.xpeppers.payments.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class PaymentsController {

    @Autowired
    PaymentService paymentService;

    @PostMapping(value = "/payments", consumes = APPLICATION_FORM_URLENCODED_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Payment> handle(@RequestBody MultiValueMap<String, String> formData) {
        UUID orderId = UUID.fromString(formData.getFirst("order_id"));

        Payment payment = paymentService.payOrderWith(orderId);

        if(payment.isCompleted()) {
            return ResponseEntity.status(201).body(payment);
        }

        return ResponseEntity.status(406).body(payment);
    }
}
