package com.cqshop.simulator.scenario.lowerproductprice;

import com.cqshop.simulator.scenario.AbstractScenario;
import com.cqshop.simulator.scenario.Action;
import com.cqshop.simulator.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by Mateusz Brycki on 2019-03-04.
 */
@Profile("lowerProductPriceScenario")
@Slf4j
@Component
public class LowerProductPriceScenario extends AbstractScenario {

    private static int COUNTER = 0;

    public LowerProductPriceScenario(UserService userService, List<Action> actions) {
        super(userService, actions);
    }


    @Override
    public void next() {
        Action actionFunction = actions.get(getAction());
        actionFunction.perform();
        COUNTER++;
    }

    private String getAction() {
        List<String> keys = new ArrayList<>(actions.keySet());
        int actionNumber = random.nextInt(keys.size());

        if (COUNTER % 2 == 0) {
            actions.get("AddProductToCart");
        } else if (COUNTER % 3 == 0) {
            actions.get("RegisterNewUser");
        } else if (COUNTER % 10 == 0) {
            actions.get("AddProductToWarehouse");
        }

        return keys.get(actionNumber);
    }

}
