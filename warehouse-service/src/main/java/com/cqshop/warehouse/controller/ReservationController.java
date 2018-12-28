package com.cqshop.warehouse.controller;

import com.cqshop.cqrs.common.gate.Gate;
import com.cqshop.warehouse.application.command.ProductReservationRemovalRequested;
import com.cqshop.warehouse.application.command.ProductReservationRequested;
import com.cqshop.warehouse.dto.CreateReservation;
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

    @PostMapping("product")
    public HttpStatus createReservation(@RequestBody CreateReservation createReservation) {

        Boolean result = gate.dispatch(ProductReservationRequested.builder()
                .productId(createReservation.getProductId())
                .quantity(createReservation.getQuantity())
                .userId(createReservation.getUserId())
                .build()
        );


        if (result) {
            return HttpStatus.CREATED;
        }

        return HttpStatus.FORBIDDEN; //TODO mbrycki find better status
    }

    @DeleteMapping("/product/{productId}/user/{userId}")
    public HttpStatus deleteReservation(@PathVariable("productId") Long productId, @PathVariable("userId") Long userId) {

       Boolean result = gate.dispatch(ProductReservationRemovalRequested.builder()
                .productId(productId)
                .userId(userId)
                .build()
        );

        if (result) {
            return HttpStatus.CREATED;
        }

        return HttpStatus.FORBIDDEN; //TODO mbrycki find better status
    }
}
