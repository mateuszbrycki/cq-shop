package com.cqshop.simulator.scenario.lowerproductprice.action;

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
import java.util.stream.Collectors;

/**
 * Created by Mateusz Brycki on 2019-02-17.
 */
@Profile("lowerProductPriceScenario")
@Slf4j
@AllArgsConstructor
@Component
public class AddProductToCart implements Action {

    private final UserService userService;
    private final ProductService productService;
    private final CartService cartService;

    private final static Random random = new Random();

    private static int ACTIONS_COUNTER = 0;
    private static int LOWER_PRICE_COUNTER = 0;
    private static int NORMAL_PRICE_COUNTER = 0;

    @Override
    public void perform() {
        List<User> users = userService.getAllUsers();
        List<Product> products = productService.getAllProducts();

        if (users.isEmpty() || products.isEmpty()) {
            return;
        }

        if ( ACTIONS_COUNTER % 10 == 0) {
            addProductWithLowerPrice(users, products);
        } else {
            addProductWithNormalPrice(users, products);
        }
        ACTIONS_COUNTER++;

    }

    /*
        1. One of the products has the set lower price
        2. a price for one of the products has changed over the time of simulation
     */

    private void addProductWithLowerPrice(List<User> users, List<Product> products) {
        products = products.stream().filter(product -> product.getPrice() <= 1).collect(Collectors.toList());

        int productAmount;
        if (LOWER_PRICE_COUNTER % 5 == 0) {
            productAmount = random.nextInt((100 - 50) + 1) + 50;
        } else {
            productAmount = random.nextInt((10 - 1) + 1) + 1;

        }

        addProduct(users, products, productAmount);
        LOWER_PRICE_COUNTER++;
    }

    private void addProductWithNormalPrice(List<User> users, List<Product> products) {
        products = products.stream().filter(product -> product.getPrice() > 1).collect(Collectors.toList());

        int productAmount;
        if (NORMAL_PRICE_COUNTER % 5 == 0) {
            productAmount = random.nextInt((100 - 50) + 1) + 50;
        } else {
            productAmount = random.nextInt((10 - 1) + 1) + 1;
        }

        addProduct(users, products, productAmount);
        NORMAL_PRICE_COUNTER++;
    }

    private void addProduct(List<User> users, List<Product> products, int productAmount) {
        int userNumber = random.nextInt(users.size());
        if (products.isEmpty()) {
            return;
        }
        int productId = random.nextInt(products.size());


        String username = users.get(userNumber).getUsername();
        Product product = products.get(productId);
        cartService.addProductToCart(username, product.getProductId(), productAmount);
        log.info("Added product to cart {user: {}, product amount: {},  price: {}", username, productAmount, product.getPrice());
    }
}
