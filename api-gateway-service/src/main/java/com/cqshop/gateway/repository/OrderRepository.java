package com.cqshop.gateway.repository;

import com.cqshop.gateway.dto.AddProductToCart;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;

/**
 * Created by Mateusz Brycki on 16/12/2018.
 */
@FeignClient("order-service")
public interface OrderRepository {

    @RequestLine("POST /api/order")
    HttpStatus createOrder();

}
