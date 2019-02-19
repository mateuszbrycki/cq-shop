package com.cqshop.warehouse.application.query;

import com.cqshop.cqrs.common.query.AbstractApplicationQueryWithTimestamp;
import com.cqshop.cqrs.common.query.ApplicationQueryAnnotation;
import lombok.*;

/**
 * Created by Mateusz Brycki on 2019-02-19.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@ApplicationQueryAnnotation
@Builder
public class FindAllAvailableProducts extends AbstractApplicationQueryWithTimestamp {
}
