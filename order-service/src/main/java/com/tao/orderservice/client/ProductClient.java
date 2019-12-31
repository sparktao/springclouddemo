package com.tao.orderservice.client;

import com.tao.orderservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "productservice", fallback =  ProductClientFallback.class)
public interface ProductClient {


    /**
     * 正常服务
     *
     * @param productCode
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/v1/product/getProduct")
    Product getProduct(String productCode);

    /**
     * 服务过期
     *
     * @param productCode
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/v1/product/getProductTimeout")
    Product getProductTimeout(String productCode);

    /**
     * 调用服务异常，选择备用方案
     *
     * @param productCode
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/v1/product/getProductFallback")
    Product getProductFallback(String productCode);

}
