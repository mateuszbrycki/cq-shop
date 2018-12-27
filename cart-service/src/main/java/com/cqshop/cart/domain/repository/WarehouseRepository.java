package com.cqshop.cart.domain.repository;

import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;

/**
 * Created by Mateusz Brycki on 27/12/2018.
 */
@FeignClient("warehouse-service")
public interface WarehouseRepository {

    @RequestLine("GET /api/reservation/product/{productId}/quantity/{quantity}?userId={userId}")
    HttpStatus reserve(@Param("productId") Long productId, @Param("quantity") Integer quantity, @Param("userId") Long userId);
}
