package com.cqshop.simulator.scenario.reservationlocking;

import com.cqshop.simulator.scenario.AbstractScenario;
import com.cqshop.simulator.scenario.Action;
import com.cqshop.simulator.service.UserService;
import com.cqshop.simulator.service.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
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
@Profile("cartInteractionScenario")
@Slf4j
@Component
public class CartInteractionScenario extends AbstractScenario {


    public CartInteractionScenario(UserService userService, List<Action> actions) {
        super(userService, actions);
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

}
