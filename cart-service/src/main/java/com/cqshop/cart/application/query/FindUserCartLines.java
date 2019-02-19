package com.cqshop.cart.application.query;

import com.cqshop.cqrs.common.query.AbstractApplicationQueryWithTimestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Created by Mateusz Brycki on 2019-02-19.
 */
@AllArgsConstructor
@Getter
@Builder
public class FindUserCartLines extends AbstractApplicationQueryWithTimestamp {

    private Long userId;
}
