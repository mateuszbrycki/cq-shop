package com.cqshop.cart.domain.service;

import com.cqshop.cart.domain.repository.WarehouseRepository;
import com.cqshop.cart.dto.CreateReservation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 27/12/2018.
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class ReservationService {

    private final WarehouseRepository warehouseRespository;

    public boolean create(long productId, int quantity, long userId) {

        HttpStatus result = warehouseRespository.reserve(CreateReservation.builder()
                .productId(productId)
                .quantity(quantity)
                .userId(userId)
                .build());

        return result.is2xxSuccessful();
    }

    public boolean remove(long productId, long userId) {
        HttpStatus result = warehouseRespository.remove(productId, userId);

        return result.is2xxSuccessful();

    }
}
