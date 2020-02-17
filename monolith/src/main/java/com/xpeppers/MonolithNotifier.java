package com.xpeppers;

public class MonolithNotifier implements Notifier {

    @Override
    public void sendEmail(String to, String body) {
        System.out.println("---------------------------------------------------");
        System.out.println("From: xcommerce");
        System.out.println("To: " + to);
        System.out.println("Body: " + body);
        System.out.println("---------------------------------------------------");
    }
}