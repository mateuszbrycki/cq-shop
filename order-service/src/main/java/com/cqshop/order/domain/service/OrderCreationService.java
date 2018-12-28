package com.cqshop.order.domain.service;

import com.cqshop.order.domain.Order;
import com.cqshop.order.domain.OrderLine;
import com.cqshop.order.domain.event.OrderCreated;
import com.cqshop.order.domain.repository.CartRepository;
import com.cqshop.order.domain.repository.OrderRepository;
import com.cqshop.order.dto.Cart;
import com.cqshop.order.infrastructure.EventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mateusz Brycki on 28/12/2018.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class OrderCreationService {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    private final EventPublisher eventPublisher;


    public Boolean createOrder(Long userId) {

        ResponseEntity<Cart> response = cartRepository.getCartForUser(userId);
        if (!response.getStatusCode().is2xxSuccessful()) {
            log.error("Cannot retrieve a cart for the user {}", userId);
            return false;
        }

        Cart cart = response.getBody();

        Order order = Order.builder()
                .creationDate(DateTime.now().toDate())
                .orderOwner(cart.getCartOwner())
                .build();

        orderRepository.save(order);

        List<OrderLine> orderLines = cart.getProducts().stream()
                .map(product -> {
                    return OrderLine.builder()
                            .price(product.getPrice())
                            .productId(product.getProductId())
                            .quantity(product.getQuantity())
                            .userOrder(order)
                            .build();
                })
                .collect(Collectors.toList());

        order.setOrderLines(orderLines);
        orderRepository.save(order);

        eventPublisher.publish(OrderCreated.builder()
                .orderId(order.getOrderId())
                .creationTime(order.getCreationDate().getTime())
                .orderOwner(order.getOrderOwner())
                .build()
        );

        return true;
    }

}
