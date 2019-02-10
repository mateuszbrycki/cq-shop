package com.cqshop.warehouse.domain.service;

import com.cqshop.warehouse.domain.MaterializedReservation;
import com.cqshop.warehouse.domain.Product;
import com.cqshop.warehouse.domain.Reservation;
import com.cqshop.warehouse.domain.event.ReservationMaterialized;
import com.cqshop.warehouse.domain.repository.MaterializedReservationRepository;
import com.cqshop.warehouse.domain.repository.ProductRepository;
import com.cqshop.warehouse.domain.repository.ReservationRepository;
import com.cqshop.warehouse.infrastructure.EventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mateusz Brycki on 28/12/2018.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class MaterializationService {

    private final ReservationRepository reservationRepository;
    private final MaterializedReservationRepository materializedReservationRepository;
    private final ProductRepository productRepository;

    private final EventPublisher eventPublisher;

    public boolean materializeReservationsForUser(Long userId) {

        List<Reservation> userReservations = reservationRepository.findAllByUserId(userId);
        if (userReservations.isEmpty()) {
            log.warn("Not found any reservations for user {}", userId);
            return false;
        }

        userReservations.forEach(this::materializeReservation);
        return true;
    }

    private void materializeReservation(Reservation reservation) {
        MaterializedReservation materializedReservation = MaterializedReservation.of(reservation);

        materializedReservation = materializedReservationRepository.save(materializedReservation);
        reservationRepository.delete(reservation);

        Product product = reservation.getProduct();
        product.setQuantity(product.getQuantity() - reservation.getQuantity());
        productRepository.save(product);

        eventPublisher.publish(
                ReservationMaterialized.builder()
                        .reservationId(reservation.getReservationId())
                        .productId(product.getProductId())
                        .quantity(reservation.getQuantity())
                        .materializationDate(materializedReservation.getMaterializationDate().getTime())
                        .userId(reservation.getUserId())
                        .build()
        );

    }
}
