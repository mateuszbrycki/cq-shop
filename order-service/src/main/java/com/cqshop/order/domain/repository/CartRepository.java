package com.cqshop.order.domain.repository;

import com.cqshop.order.dto.Cart;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;

/**
 * Created by Mateusz Brycki on 28/12/2018.
 */
@FeignClient("cart-service")
public interface CartRepository {

    @RequestLine("GET /api/cart/user/{userId}")
    ResponseEntity<Cart> getCartForUser(@Param("userId") Long userId);
}
