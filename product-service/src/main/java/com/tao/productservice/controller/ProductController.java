package com.tao.productservice.controller;

import com.tao.productservice.model.Product;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "v1/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private HttpServletRequest request;

    @PostMapping("/getProduct")
    public Product getProduct(@RequestBody String productCode) {

        logger.info("Get Product by code: {} from port: {}", productCode, request.getServerPort());

        Product product = getProduct();

        return product;
    }

    @PostMapping("/getProductTimeout")
    public Product getProductTimeout(@RequestBody String productCode) {

        logger.info("Get Product by code: {} from port: {}", productCode, request.getServerPort());

        try {
            TimeUnit.SECONDS.sleep(RandomUtils.nextInt(5) + 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Product product = getProduct();

        return product;
    }

    @PostMapping("/getProductFallback")
    public Product getProductFallback(@RequestBody String productCode) {

        throw new RuntimeException("ProductController 获取产品服务失败");
    }

    private Product getProduct() {
        Product product = new Product();
        product.setId(1l);
        product.setPrice(100f);
        product.setProductCode("product1");
        product.setProductName("Microservice Book");
        product.setDescription("Description of Microservice");
        return product;
    }


}
