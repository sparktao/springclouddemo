package com.tao.orderservice.controller;

import com.tao.orderservice.model.Order;
import com.tao.orderservice.service.OrderService;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/saveOrder")
    public Order saveOrder(@RequestBody String productCode) {
        Order order = orderService.addOrder(productCode);
        return order;
    }

    @PostMapping("/saveOrderTimeout")
    public Order saveOrderTimeout(@RequestBody String productCode) {
        try {
            TimeUnit.SECONDS.sleep(RandomUtils.nextInt(5) + 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Order order = orderService.addOrderTimeout(productCode);
        return order;
    }

    @PostMapping("/saveOrderFallback")
    public Order saveOrderFallback(@RequestBody String productCode) {
        Order order = orderService.addOrderFallback(productCode);
        return order;
    }

}
