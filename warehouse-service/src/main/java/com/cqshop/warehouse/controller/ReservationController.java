package com.cqshop.warehouse.controller;

import com.cqshop.cqrs.common.gate.Gate;
import com.cqshop.warehouse.application.command.ProductReservationRequested;
import com.netflix.ribbon.proxy.annotation.Http;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Mateusz Brycki on 27/12/2018.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    private final Gate gate;

    @GetMapping("product/{productId}/quantity/{quantity}")
    public HttpStatus createReservation(@PathVariable("productId") Long productId,
                                        @PathVariable("quantity") int quantity,
                                        @RequestParam(value = "userId")  Long userId) {

        Boolean result = gate.dispatch(ProductReservationRequested.builder()
                .productId(productId)
                .quantity(quantity)
                .userId(userId)
                .build()
        );


        if (result) {
            return HttpStatus.CREATED;
        }

        return HttpStatus.FORBIDDEN; //TODO mbrycki find better status
    }
}
