package com.xpeppers.payments.web;

import org.springframework.stereotype.Component;
import org.xml.sax.InputSource;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.StringReader;
import java.util.Random;

import static kong.unirest.Unirest.get;

@Component
public class EurekaClient {

    private static final String ENDPOINT_URL = "http://eureka-server:8181/eureka/apps/NOTIFICATIONS";

    public String getNotificationServiceUrl() {
        try {
            String body = get(ENDPOINT_URL).asString().getBody();
            return randomServiceUrl(body);
        } catch (Exception e) {
            throw new RuntimeException("Cannot retrieve any service for notifications");
        }
    }

    private String randomServiceUrl(String body) throws IOException, XPathExpressionException {
        Integer pickedInstance = randomInstanceFrom(body);
        String port = extractBy(body, "/application/instance[" + pickedInstance + "]/port/text()");
        String ip = extractBy(body, "/application/instance[" + pickedInstance + "]/ipAddr/text()");
        return "http://" + ip + ":" + port;
    }

    private Integer randomInstanceFrom(String body) throws XPathExpressionException {
        int instancesNumber = Integer.parseInt(extractBy(body, "count(/application/instance)"));
        return new Random().nextInt(instancesNumber) + 1;
    }

    private String extractBy(String body, String expression) throws XPathExpressionException {
        InputSource inputSource = new InputSource(new StringReader(body));
        XPath xpath = XPathFactory.newInstance().newXPath();
        return xpath.evaluate(expression, inputSource);
    }

}
