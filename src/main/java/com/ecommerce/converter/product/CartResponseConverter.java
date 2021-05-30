package com.ecommerce.converter.product;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ecommerce.model.dto.CartItemDto;
import com.ecommerce.model.entity.Cart;
import com.ecommerce.model.response.CartResponse;
@Component
public class CartResponseConverter implements Function<Cart, CartResponse>{

	@Override
	public CartResponse apply(Cart cart) {
		CartResponse cartResponse = new CartResponse();
		
		cartResponse.setCartItems(cart.getCartItemList()
	                .stream()
	                .map(cartItem -> CartItemDto
	                        .builder()
	                        .id(cartItem.getId())
	                        .name(cartItem.getProduct().getName())
	                        .price(cartItem.getProduct().getPrice())
	                        .stock(cartItem.getProduct().getStock())
	                        .onSalePrice(cartItem.getProduct().getOnSalePrice())
	                        .amount(cartItem.getAmount())
	                        .productId(cartItem.getProduct().getId())
	                        .build())
	                .collect(Collectors.toList()));
		cartResponse.setTotalCartPrice(cart.getTotalCartPrice());
        cartResponse.setTotalCargoPrice(cart.getTotalCargoPrice());
        cartResponse.setTotalPrice(cart.getTotalPrice());
		return cartResponse;
	}

	

}
