package com.xpeppers;

import static kong.unirest.Unirest.post;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

public class MicroserviceNotifier implements Notifier {

    private EurekaClient eurekaClient;

    public MicroserviceNotifier(EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }

    @Override
    public void sendEmail(String to, String body) {
        String url = eurekaClient.getNotificationServiceUrl() + "/v1/notifications";

        Notification notification = new Notification(to, body);
        post(url)
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