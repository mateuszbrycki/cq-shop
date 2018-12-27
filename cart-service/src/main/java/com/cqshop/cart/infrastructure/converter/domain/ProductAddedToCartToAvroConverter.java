package com.cqshop.cart.infrastructure.converter.domain;

import com.cqshop.cart.domain.event.ProductAddedToCart;
import com.cqshop.converter.TypeConverter;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Mateusz Brycki on 27/12/2018.
 */
@TypeConverter
public class ProductAddedToCartToAvroConverter implements Converter<ProductAddedToCart, com.cqshop.cart.avro.ProductAddedToCart> {

    @Override
    public com.cqshop.cart.avro.ProductAddedToCart convert(ProductAddedToCart source) {

        com.cqshop.cart.avro.ProductAddedToCart productAddedToCart = new com.cqshop.cart.avro.ProductAddedToCart();
        productAddedToCart.setCartId(source.getCartId());
        productAddedToCart.setProductId(source.getProductId());
        productAddedToCart.setPrice(source.getPrice());
        productAddedToCart.setQuantity(source.getQuantity());
        productAddedToCart.setTimestamp(System.currentTimeMillis());

        return productAddedToCart;
    }
}
