package com.cqshop.simulator.scenario;

import com.cqshop.simulator.service.UserService;
import com.cqshop.simulator.service.dto.User;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by Mateusz Brycki on 2019-02-09.
 */
public abstract class AbstractScenario {

    public abstract void next();

    protected final UserService userService;
    protected final Map<String, Action> actions;

    protected final static Random random = new Random();

    public AbstractScenario(UserService userService, List<Action> actions) {
        this.userService = userService;
        this.actions = actions.stream()
                .collect(Collectors.toMap(action -> action.getClass().getSimpleName(), action -> action));

        registerAdminUser();
    }


    @PostConstruct
    protected void run() {
        while(true) {
            try {
                next();
            } catch (org.springframework.web.client.HttpServerErrorException.GatewayTimeout e) {
                e.printStackTrace();
            }
        }
    }

    protected void registerAdminUser() {

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
