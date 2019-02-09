package com.cqshop.simulator.scenario.reservationlocking;

import com.cqshop.simulator.scenario.AbstractScenario;
import com.cqshop.simulator.scenario.Action;
import com.cqshop.simulator.service.CartService;
import com.cqshop.simulator.service.OrderService;
import com.cqshop.simulator.service.ProductService;
import com.cqshop.simulator.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Mateusz Brycki on 2019-02-09.
 */
@Slf4j
@Component

public class CartInteractionScenario extends AbstractScenario {

    private final UserService userService;
    private final ProductService productService;
    private final CartService cartService;
    private final OrderService orderService;

    private final static Random random = new Random();
    private static Integer actorsAmount = 0;
    private static Integer productsAmount = 0;

    private final Map<Integer, Action> actions = new HashMap<>();

    public CartInteractionScenario(UserService userService, ProductService productService, CartService cartService, OrderService orderService) {
        this.userService = userService;
        this.productService = productService;
        this.cartService = cartService;
        this.orderService = orderService;
        actions.put(0, this::registerNewUser);
        actions.put(1, this::addProductToWarehouse);
        actions.put(2, this::addProductToCart);
        actions.put(3, this::removeProductFromCart);
        actions.put(4, this::submitOrder);
    }

    @PostConstruct
    public void run() {
        while(true) {
            next();
        }
    }

    @Override
    public void next() {
        int action = getAction();

        Action actionFunction = actions.get(action);
        actionFunction.perform();
    }

    private int getAction() {
        return random.nextInt(actions.size() - 1);
    }

    private void registerNewUser() {
        int id = actorsAmount++;
        userService.registerUser("user-" + id);
        log.info("Registered user with id " + id);
    }

    private void addProductToWarehouse() {
        int id = productsAmount++;
        productService.createProduct("product-" + id);
        log.info("Added product with id " + id);
    }

    private void addProductToCart() {

        //TODO mbrycki retrieve list of users and products
        if (actorsAmount < 1|| productsAmount < 1) {
            return;
        }

        int userId = random.nextInt(actorsAmount);
        int productId = random.nextInt(productsAmount);

        int productAmount = random.nextInt(10);
        if (userId % 5 == 0) {
            productAmount *=5;
        }

        cartService.addProductToCart(userId, productId, productAmount);
        log.info("Added product to cart {cart: " + userId + ", product amount: " + productAmount + "}");
    }

    private void removeProductFromCart() {

        //TODO mbrycki retrieve list of users and products
        if (actorsAmount < 1 || productsAmount < 1) {
            return;
        }
        int userId = random.nextInt(actorsAmount);
        int productId = random.nextInt(productsAmount);
        int productAmount = random.nextInt(10);

        cartService.removeProductFromCart(userId, productId, productAmount);
        log.info("Removed product from cart " + userId + " product id: " + productId);

    }

    private void submitOrder() {

        //TODO mbrycki retrieve list of users
        if (actorsAmount < 1) {
            return;
        }
        int userId = random.nextInt(actorsAmount);
        orderService.submitOrder(userId);
        log.info("Submitted order " + userId);
    }

}
