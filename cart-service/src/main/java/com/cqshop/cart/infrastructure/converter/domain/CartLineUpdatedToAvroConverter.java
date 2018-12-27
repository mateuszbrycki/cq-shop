package com.cqshop.cart.infrastructure.converter.domain;

import com.cqshop.cart.domain.event.CartLineUpdated;
import com.cqshop.converter.TypeConverter;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Mateusz Brycki on 27/12/2018.
 */
@TypeConverter
public class CartLineUpdatedToAvroConverter implements Converter<CartLineUpdated, com.cqshop.cart.avro.CartLineUpdated> {

    @Override
    public com.cqshop.cart.avro.CartLineUpdated convert(CartLineUpdated source) {

        com.cqshop.cart.avro.CartLineUpdated cartLineUpdated = new com.cqshop.cart.avro.CartLineUpdated();
        cartLineUpdated.setCartLineId(source.getCartLineId());
        cartLineUpdated.setProductId(source.getProductId());
        cartLineUpdated.setPrice(source.getPrice());
        cartLineUpdated.setQuantity(source.getQuantity());
        cartLineUpdated.setTimestamp(System.currentTimeMillis());

        return cartLineUpdated;
    }
}
