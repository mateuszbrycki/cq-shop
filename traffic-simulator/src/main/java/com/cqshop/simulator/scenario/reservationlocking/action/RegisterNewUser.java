package com.cqshop.simulator.scenario.reservationlocking.action;

import com.cqshop.simulator.scenario.Action;
import com.cqshop.simulator.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by Mateusz Brycki on 2019-02-17.
 */
@Profile("cartInteractionScenario")
@Slf4j
@AllArgsConstructor
@Component
public class RegisterNewUser implements Action {

    private final UserService userService;

    @Override
    public void perform() {
        String id = UUID.randomUUID().toString();
        userService.registerUser("user-" + id);
        log.info("Registered user with id " + id);
    }
}
