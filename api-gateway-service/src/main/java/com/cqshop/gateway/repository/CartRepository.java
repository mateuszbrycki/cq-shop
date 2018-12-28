package com.cqshop.gateway.repository;

import com.cqshop.gateway.dto.*;
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

    @RequestLine("POST /api/cart/product")
    HttpStatus addProductToCart(AddProductToCart addProductToCart);

    @RequestLine("DELETE /api/cart/product/{productId}")
    HttpStatus removeProductFromCart(@Param("productId") Long productId);

}
