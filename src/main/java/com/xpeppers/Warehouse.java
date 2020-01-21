package com.xpeppers;

import java.util.*;

public class Warehouse {
    private static final Map<String, Integer> products = new HashMap<String, Integer>() {{
        put("111", 1);
        put("222", 2);
        put("333", 3);
        put("444", 4);
        put("555", 5);
    }};

    private static final List<ReservedProduct> reservedProducts = new ArrayList<>();

    public Order reserveProductsFor(Order order) {
        if (productsCanBeReservedFor(order)) {
            reserveFor(order);
            System.out.println("---------------------------------------------------");
            System.out.println("From: xcommerce");
            System.out.println("To: user@wonderfuldomain.com");
            System.out.println("Body: your order for " + order.productCode() + " has been reserved.");
            System.out.println("---------------------------------------------------");
            return order.reserved();
        }
        return order.placed();
    }

    private boolean productsCanBeReservedFor(Order order) {
        return products.get(order.productCode()) >= order.productQuantity();
    }

    private void reserveFor(Order order) {
        UUID orderId = order.id();
        String productCode = order.productCode();
        Integer productQuantity = order.productQuantity();

        int newProductQuantity = products.get(productCode) - productQuantity;

        products.put(productCode, newProductQuantity);
        reservedProducts.add(new ReservedProduct(orderId, productCode, productQuantity));
    }
}
