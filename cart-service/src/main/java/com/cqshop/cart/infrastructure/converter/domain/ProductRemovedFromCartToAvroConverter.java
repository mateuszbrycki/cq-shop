package com.cqshop.cart.infrastructure.converter.domain;

import com.cqshop.cart.domain.event.ProductRemovedFromCart;
import com.cqshop.converter.TypeConverter;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Mateusz Brycki on 28/12/2018.
 */
@TypeConverter
public class ProductRemovedFromCartToAvroConverter implements Converter<ProductRemovedFromCart, com.cqshop.cart.avro.ProductRemovedFromCart> {

    @Override
    public com.cqshop.cart.avro.ProductRemovedFromCart convert(ProductRemovedFromCart source) {

        com.cqshop.cart.avro.ProductRemovedFromCart productRemovedFromCart = new com.cqshop.cart.avro.ProductRemovedFromCart();
        productRemovedFromCart.setCartId(source.getCartId());
        productRemovedFromCart.setProductId(source.getProductId());
        productRemovedFromCart.setCartLineId(source.getCartLineId());
        productRemovedFromCart.setTimestamp(System.currentTimeMillis());

        return productRemovedFromCart;
    }
}
