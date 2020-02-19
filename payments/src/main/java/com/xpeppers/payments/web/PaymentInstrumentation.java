package com.xpeppers.payments.web;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class PaymentInstrumentation {
    private MeterRegistry meterRegistry;
    private Counter successfulPayments;
    private Counter failedPayments;

    public PaymentInstrumentation(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        this.successfulPayments = this.meterRegistry.counter("payments", "type", "successful");
        this.failedPayments = this.meterRegistry.counter("payments", "type", "failed");
    }

    public void paymentProcessed(Payment payment) {
        if(payment.isCompleted()) {
            this.successfulPayments.increment();
        } else {
            this.failedPayments.increment();
        }
    }
}
