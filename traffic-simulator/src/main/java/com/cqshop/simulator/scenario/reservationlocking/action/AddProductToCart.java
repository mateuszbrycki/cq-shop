package com.cqshop.simulator.scenario.reservationlocking.action;

import com.cqshop.simulator.scenario.Action;
import com.cqshop.simulator.service.CartService;
import com.cqshop.simulator.service.ProductService;
import com.cqshop.simulator.service.UserService;
import com.cqshop.simulator.service.dto.Product;
import com.cqshop.simulator.service.dto.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

/**
 * Created by Mateusz Brycki on 2019-02-17.
 */
@Profile("cartInteractionScenario")
@Slf4j
@AllArgsConstructor
@Component
public class AddProductToCart implements Action {

    private final UserService userService;
    private final ProductService productService;
    private final CartService cartService;

    private final static Random random = new Random();

    @Override
    public void perform() {
        List<User> users = userService.getAllUsers();
        List<Product> products = productService.getAllProducts();

        if (users.isEmpty() || products.isEmpty()) {
            return;
        }

        int userNumber = random.nextInt(users.size());
        int productId = random.nextInt(products.size());

        int productAmount = random.nextInt(10) + 1;
        if (userNumber % 5 == 0) {
            productAmount *=5;
        }

        String username = users.get(userNumber).getUsername();
        cartService.addProductToCart(username, productId, productAmount);
        log.info("Added product to cart {cart: " + username + ", product amount: " + productAmount + "}");
    }
}
