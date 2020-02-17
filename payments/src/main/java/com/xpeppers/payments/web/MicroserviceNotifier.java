package com.xpeppers.payments.web;

import org.springframework.stereotype.Component;

import static kong.unirest.Unirest.post;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

@Component
public class MicroserviceNotifier implements Notifier {

    @Override
    public void sendEmail(String to, String body) {
        Notification notification = new Notification(to, body);
        post("http://proxy/v1/notifications")
                .header("Content-Type", APPLICATION_JSON.toString())
                .body(notification)
                .asEmpty();
    }

    private class Notification {
        final String from = "xcommerce";
        final String to;
        final String body;

        public Notification(String to, String body) {
            this.to = to;
            this.body = body;
        }
    }
}