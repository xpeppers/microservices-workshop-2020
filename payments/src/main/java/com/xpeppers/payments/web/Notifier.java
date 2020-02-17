package com.xpeppers.payments.web;

public interface Notifier {
    void sendEmail(String to, String body);
}
