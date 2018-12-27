package com.cqshop.gateway.repository;

import com.cqshop.gateway.dto.RegisterAccountRequest;
import com.cqshop.gateway.dto.UpdateAccountRequest;
import com.cqshop.gateway.dto.UserAuthResponse;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by Mateusz Brycki on 16/12/2018.
 */
@FeignClient("cart-service")
public interface CartRepository {

    @RequestLine("GET /api/cart/product/{productId}?quantity={quantity}")
    HttpStatus addProductToCart(@Param("productId") long productId, @Param("quantity") int quantity);

}
