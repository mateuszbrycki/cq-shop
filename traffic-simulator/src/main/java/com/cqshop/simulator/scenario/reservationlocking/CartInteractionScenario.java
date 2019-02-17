package com.cqshop.simulator.scenario.reservationlocking;

import com.cqshop.simulator.scenario.AbstractScenario;
import com.cqshop.simulator.scenario.Action;
import com.cqshop.simulator.service.CartService;
import com.cqshop.simulator.service.OrderService;
import com.cqshop.simulator.service.ProductService;
import com.cqshop.simulator.service.UserService;
import com.cqshop.simulator.service.dto.Product;
import com.cqshop.simulator.service.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

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
    private final Map<Integer, Action> actions = new HashMap<>();

    public CartInteractionScenario(UserService userService, ProductService productService, CartService cartService, OrderService orderService) {
        this.userService = userService;
        this.productService = productService;
        this.cartService = cartService;
        this.orderService = orderService;

        actions.put(0, this::addProductToCart);
        actions.put(1, this::removeProductFromCart);
        actions.put(2, this::submitOrder);
        actions.put(3, this::registerNewUser);
        actions.put(4, this::addProductToWarehouse);

        registerAdminUser();
    }

    private void registerAdminUser() {

        for (User user : userService.getAllUsers()) {
            if (user.getUsername().equals("admin")) {
                return;
            }
        }

        userService.registerUser("admin");
    }

    @PostConstruct
    public void run() {
        while(true) {
            try {
                next();
            } catch (org.springframework.web.client.HttpServerErrorException.GatewayTimeout e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void next() {
        int action = getAction();

        if(action % 2 == 0) {

            int i = random.nextInt(2);
            if (i % 2 == 0) {
                actions.get(0).perform(); //addProductToCart
            } else {
                actions.get(1).perform(); //remove product from cart
            }
        }

        if (action % 3 == 0) {
            actions.get(2).perform(); //submit order
        }

        if (action % 5 == 0) {
            int i = random.nextInt(2);
            if (i % 2 == 0) {
                actions.get(3).perform(); //register new user
            } else {
                actions.get(4).perform(); //add product to warehouse
            }
        }

        Action actionFunction = actions.get(action);
        actionFunction.perform();
    }

    private int getAction() {
        return random.nextInt(actions.size());
    }

    private void registerNewUser() {
        String id = UUID.randomUUID().toString();
        userService.registerUser("user-" + id);
        log.info("Registered user with id " + id);
    }

    private void addProductToWarehouse() {
        String id = UUID.randomUUID().toString();
        productService.createProduct("product-" + id);
        log.info("Added product with id " + id);
    }

    private void addProductToCart() {

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

    private void removeProductFromCart() {

        List<User> users = userService.getAllUsers();
        List<Product> products = productService.getAllProducts();

        if (users.isEmpty() || products.isEmpty()) {
            return;
        }

        int userNumber = random.nextInt(users.size());
        int productId = random.nextInt(products.size());
        int productAmount = random.nextInt(10) + 1;

        String username = users.get(userNumber).getUsername();
        cartService.removeProductFromCart(username, productId, productAmount);
        log.info("Removed product from cart " + username + " product id: " + productId);

    }

    private void submitOrder() {

        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return;
        }
        int userNumber = random.nextInt(users.size());
        String username = users.get(userNumber).getUsername();
        orderService.submitOrder(username);
        log.info("Submitted order " + username);
    }

}
