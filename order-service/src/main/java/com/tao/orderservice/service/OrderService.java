package com.tao.orderservice.service;

import com.tao.orderservice.client.ProductClient;
import com.tao.orderservice.model.Order;
import com.tao.orderservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService {

    @Autowired
    private ProductClient productClient;

    public Order addOrder(String productCode) {
        Order order = new Order();
        order.setId(1l);

        Product product = productClient.getProduct(productCode);
        if (product == null) {
            return order;
        }
        order.setItem(product.getProductName());
        order.setCreatedTime(new Date());

        return order;
    }

    public Order addOrderTimeout(String productCode) {
        Order order = new Order();
        order.setId(1l);

        Product product = productClient.getProductTimeout(productCode);
        if (product == null) {
            return order;
        }
        order.setItem(product.getProductName());
        order.setCreatedTime(new Date());

        return order;
    }

    public Order addOrderFallback(String productCode) {
        Order order = new Order();
        order.setId(1l);

        Product product = productClient.getProductFallback(productCode);
        if (product == null) {
            return order;
        }
        order.setItem(product.getProductName());
        order.setCreatedTime(new Date());

        return order;
    }

}
