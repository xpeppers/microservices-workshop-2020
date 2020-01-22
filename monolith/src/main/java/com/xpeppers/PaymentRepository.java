package com.xpeppers;

import java.util.ArrayList;
import java.util.List;

public class PaymentRepository {
    private static final List<Payment> payments = new ArrayList<>();

    public void add(Payment order) {
        payments.add(order);
    }
}
