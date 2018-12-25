package com.cqshop.cart.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Mateusz Brycki on 25/12/2018.
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name="user_cart")
public class Cart {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long cartId;

    private Long cartOwner;

    @NotNull
    private Date creationDate;
}
