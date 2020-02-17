package com.xpeppers.notifications.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationsController {

    @PostMapping(value = "/v1/notifications", consumes = "application/json")
    public ResponseEntity handle(@RequestBody Notification notification) {
        System.out.println("---------------------------------------------------");
        System.out.println("From: " + notification.getFrom());
        System.out.println("To: " + notification.getTo());
        System.out.println("Body: " + notification.getBody());
        System.out.println("---------------------------------------------------");
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
