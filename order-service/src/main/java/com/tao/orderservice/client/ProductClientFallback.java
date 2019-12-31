package com.tao.orderservice.client;

import com.tao.orderservice.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductClientFallback implements ProductClient {
    @Override
    public Product getProduct(String productCode) {
        return null;
    }

    @Override
    public Product getProductTimeout(String productCode) {
        return null;
    }

    @Override
    public Product getProductFallback(String productCode) {
        Product product = new Product();
        product.setId(2l);
        product.setProductName("备用产品");

        return product;
    }

}
