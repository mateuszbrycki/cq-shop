package com.cqshop.cart.application.command;

import com.cqshop.cqrs.common.command.AbstractApplicationCommand;
import com.cqshop.cqrs.common.command.ApplicationCommandAnnotation;
import lombok.*;

import java.io.Serializable;

/**
 * Created by Mateusz Brycki on 27/12/2018.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@ApplicationCommandAnnotation
@Builder
public class ProductRemovalFromCartRequested extends AbstractApplicationCommand implements Serializable {

    @NonNull
    private long productId;

    @NonNull
    private long userId;

}
