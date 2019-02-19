package com.cqshop.usermanagement.application.query;

import com.cqshop.cqrs.common.query.AbstractApplicationQueryWithTimestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Created by Mateusz Brycki on 2019-02-19.
 */
@Builder
@AllArgsConstructor
@Getter
public class FindUserByUsername extends AbstractApplicationQueryWithTimestamp {

    private final String username;
}
