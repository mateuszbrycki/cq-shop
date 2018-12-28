package com.cqshop.order.controller;

import com.cqshop.cqrs.common.gate.Gate;
import com.cqshop.order.application.command.OrderCreationRequested;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Mateusz Brycki on 28/12/2018.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final Gate gate;

    @PostMapping
    public HttpStatus createOrder(@RequestHeader("X-User-Id") Long userId) {

        Boolean result = gate.dispatch(OrderCreationRequested.builder()
                .userId(userId)
                .build()
        );

        if (result) {
            return HttpStatus.CREATED;
        }

        return HttpStatus.FORBIDDEN; //TODO mbrycki find better status
    }


}
