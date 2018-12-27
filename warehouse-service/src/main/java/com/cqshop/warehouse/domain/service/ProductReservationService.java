package com.cqshop.warehouse.domain.service;

import com.cqshop.warehouse.domain.Product;
import com.cqshop.warehouse.domain.Reservation;
import com.cqshop.warehouse.domain.event.ProductReservationCreated;
import com.cqshop.warehouse.domain.repository.ProductRepository;
import com.cqshop.warehouse.domain.repository.ReservationRepository;
import com.cqshop.warehouse.infrastructure.EventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Mateusz Brycki on 27/12/2018.
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class ProductReservationService {

    private final ReservationRepository reservationRepository;
    private final ProductRepository productRepository;

    private final EventPublisher eventPublisher;

    public boolean reserve(Long productId, Integer quantity, Long userId) {

        //TODO mbrycki create a reservation

        Optional<Product> productOptional = productRepository.findById(productId);

        if (!productOptional.isPresent()) {
            log.error("Cannot find a product with ID {}", productId);
            return false;
        }

        Product product = productOptional.get();
        boolean isAvailable = checkProductAvailability(product, quantity);

        if (!isAvailable) {
            log.error("There is not enough amount of the product with ID {}", productId);
            return false;
        }

        Reservation reservation = Reservation.builder()
                .quantity(quantity)
                .product(product)
                .reservationDate(DateTime.now().toDate())
                .build();

        reservationRepository.save(reservation);

        eventPublisher.publish(ProductReservationCreated.builder()
                .id(reservation.getReservationId())
                .productId(product.getProductId())
                .quantity(quantity)
                .userId(userId)
                .build()
        );
        return true;

    }

    private boolean checkProductAvailability(Product product, Integer quantity) {

        List<Reservation> reservations = reservationRepository.findAllByProductProductId(product.getProductId());

        Integer reservationsSum = reservations.stream()
                .mapToInt(Reservation::getQuantity)
                .sum();

        return (product.getQuantity() - reservationsSum) >= quantity;

    }
}
