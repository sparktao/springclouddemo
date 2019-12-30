package com.tao.orderservice.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.tao.orderservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductClientBulkhead {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 正常服务
     *
     * @param productCode
     * @return
     */
    public Product getProduct(String productCode) {
        ResponseEntity<Product> result =
                restTemplate.postForEntity("http://productservice/v1/product/getProduct", productCode, Product.class);

        return result.getBody();
    }

    /**
     * 服务过期
     *
     * @param productCode
     * @return
     */
    @HystrixCommand(
            threadPoolKey = "getProductTimeout",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "2"),
                    @HystrixProperty(name = "maxQueueSize", value = "20")
            },
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            }
    )
    public Product getProductTimeout(String productCode) {
        ResponseEntity<Product> result =
                restTemplate.postForEntity("http://productservice/v1/product/getProductTimeout", productCode, Product.class);

        return result.getBody();
    }

    /**
     * 调用服务异常，选择备用方案
     *
     * @param productCode
     * @return
     */
    @HystrixCommand(
            threadPoolKey = "getProductFallback",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "1"),
                    @HystrixProperty(name = "maxQueueSize", value = "20")
            },
            fallbackMethod = "getProductFallback0")
    public Product getProductFallback(String productCode) {
        ResponseEntity<Product> result =
                restTemplate.postForEntity("http://productservice/v1/product/getProductFallback", productCode, Product.class);

        return result.getBody();
    }

    public Product getProductFallback0(String productCode) {
        Product product = new Product();
        product.setId(2l);
        product.setProductName("备用产品");

        return product;
    }
}
