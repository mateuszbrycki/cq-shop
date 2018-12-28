package com.cqshop.cart.infrastructure.converter.domain;

import com.cqshop.cart.domain.event.CartLineRemoved;
import com.cqshop.cart.domain.event.CartLineUpdated;
import com.cqshop.converter.TypeConverter;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Mateusz Brycki on 27/12/2018.
 */
@TypeConverter
public class CartLineRemovedToAvroConverter implements Converter<CartLineRemoved, com.cqshop.cart.avro.CartLineRemoved> {

    @Override
    public com.cqshop.cart.avro.CartLineRemoved convert(CartLineRemoved source) {

        com.cqshop.cart.avro.CartLineRemoved cartLineRemoved = new com.cqshop.cart.avro.CartLineRemoved();
        cartLineRemoved.setCartLineId(source.getCartLineId());
        cartLineRemoved.setProductId(source.getProductId());
        cartLineRemoved.setPrice(source.getPrice());
        cartLineRemoved.setQuantity(source.getQuantity());
        cartLineRemoved.setUserId(source.getUserId());
        cartLineRemoved.setTimestamp(System.currentTimeMillis());

        return cartLineRemoved;
    }
}
