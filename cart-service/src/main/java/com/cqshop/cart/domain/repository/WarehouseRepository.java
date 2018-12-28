package com.cqshop.cart.domain.repository;

import com.cqshop.cart.dto.CreateReservation;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;

/**
 * Created by Mateusz Brycki on 27/12/2018.
 */
@FeignClient("warehouse-service")
public interface WarehouseRepository {

    @RequestLine("POST /api/reservation/product")
    HttpStatus reserve(CreateReservation createReservation);

    @RequestLine("DELETE /api/reservation/product/{productId}/user/{userId}")
    HttpStatus remove(@Param("productId") Long productId, @Param("userId") Long userId);
}
