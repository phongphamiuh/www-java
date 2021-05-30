package com.ecommerce.service;

import com.ecommerce.model.entity.Cart;
import com.ecommerce.model.response.CartResponse;

public interface CartService {
		CartResponse addToCart(Long id, Integer amount);
	
	    CartResponse incrementCartItem(Long cartItemId, Integer amount);
	
	    CartResponse decrementCartItem(Long cartItemId, Integer amount);
	
	    CartResponse fetchCart();
	
	    CartResponse removeFromCart(Long id);
	
	   // boolean confirmCart(ConfirmCartRequest confirmCartRequest);
	
	    Cart getCart();
	
	    void saveCart(Cart cart);
	
	    void emptyCart();
	
	    Cart calculatePrice(Cart cart);
}
