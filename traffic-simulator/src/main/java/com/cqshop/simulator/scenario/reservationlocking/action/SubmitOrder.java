package com.cqshop.simulator.scenario.reservationlocking.action;

import com.cqshop.simulator.scenario.Action;
import com.cqshop.simulator.service.OrderService;
import com.cqshop.simulator.service.UserService;
import com.cqshop.simulator.service.dto.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

/**
 * Created by Mateusz Brycki on 2019-02-17.
 */
@Slf4j
@AllArgsConstructor
@Component
public class SubmitOrder implements Action {

    private final UserService userService;
    private final OrderService orderService;

    private final static Random random = new Random();

    @Override
    public void perform() {
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
