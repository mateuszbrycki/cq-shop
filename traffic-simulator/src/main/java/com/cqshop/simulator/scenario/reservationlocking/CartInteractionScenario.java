package com.cqshop.simulator.scenario.reservationlocking;

import com.cqshop.simulator.scenario.AbstractScenario;
import com.cqshop.simulator.scenario.Action;
import com.cqshop.simulator.service.UserService;
import com.cqshop.simulator.service.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by Mateusz Brycki on 2019-02-09.
 */
@Slf4j
@Component
public class CartInteractionScenario extends AbstractScenario {

    private final UserService userService;
    private final Map<String, Action> actions;

    private final static Random random = new Random();

    public CartInteractionScenario(UserService userService, List<Action> actions) {
        this.userService = userService;
        this.actions = actions.stream()
                .collect(Collectors.toMap(action -> action.getClass().getSimpleName(), action -> action));

        registerAdminUser();
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
        Action actionFunction = actions.get(getAction());
        actionFunction.perform();
    }

    private String getAction() {
        List<String> keys = new ArrayList<>(actions.keySet());
        return keys.get(random.nextInt(keys.size()));
    }


    private void registerAdminUser() {

        try {

            for (User user : userService.getAllUsers()) {
                if (user.getUsername().equals("admin")) {
                    return;
                }
            }
        } catch(org.springframework.web.client.HttpClientErrorException.Unauthorized e) {
            e.printStackTrace();
        }

        try {
            userService.registerUser("admin");
        } catch (org.springframework.web.client.HttpServerErrorException.GatewayTimeout e) {
            e.printStackTrace();
        }

    }
}
