package com.ecommerce.model.response;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.ecommerce.model.dto.CartItemDto;

public class CartSession {
	
    private List<CartItemDto> cartItems = new ArrayList<CartItemDto>();;
    
    private CartItemDto findByItemId(Long id) {
    	System.out.println(id);
        for (CartItemDto line : this.cartItems) {
            if (line.getProductId().equals(id)) {
                return line;
            }
        }
        return null;
    }
    
    public void addProduct(CartItemDto cartItem, int amount) {
    	System.out.println("Add Product");
    	CartItemDto line = this.findByItemId(cartItem.getProductId());
    	System.out.println(cartItems);
        if (line == null) {
            line = new CartItemDto();
            line.setAmount(cartItem.getAmount());
            line.setName(cartItem.getName());
            line.setOnSalePrice(cartItem.getOnSalePrice());
            line.setPrice(cartItem.getPrice());
            line.setStock(cartItem.getStock());
            line.setCargoPrice(cartItem.getCargoPrice());
            line.setProductId(cartItem.getProductId());
           
            this.cartItems.add(line);
        }else {
        	 int newQuantity = line.getAmount() + amount;
             if (newQuantity <= 0) {
                 this.cartItems.remove(line);
             } else {
                 line.setAmount(newQuantity);
             }
        }
    }

	public Float getTotalCartPrice() {
		Float totalCart = 0f;
		for (CartItemDto cartItem : cartItems) {
			totalCart += cartItem.getOnSalePrice() * cartItem.getAmount();
		}
		return roundTwoDecimals(totalCart);
	}

	public Float getTotalCargoPrice() {
		Float totalCargo = 0f;
		for (CartItemDto cartItem : cartItems) {
			totalCargo += cartItem.getCargoPrice() * cartItem.getAmount();
		}
		return roundTwoDecimals(totalCargo);
	}

	public Float getTotalPrice() {
		Float totalPrice = 0f;
		for (CartItemDto cartItem : cartItems) {
			totalPrice += (cartItem.getOnSalePrice()+ cartItem.getCargoPrice()) * cartItem.getAmount();
		}
		return roundTwoDecimals(totalPrice);
	}

	public List<CartItemDto> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItemDto> cartItems) {
		this.cartItems = cartItems;
	}
	
	 public void removeProduct(Long id) {
		CartItemDto line = this.findByItemId(id);
        if (line != null) {
            this.cartItems.remove(line);
        }
	 }
	 
	 public void updateQuantity(Long id,Integer amount) {
		CartItemDto line = this.findByItemId(id);
		if(amount == 0) {
			this.cartItems.remove(line);
		}
        if (line != null) {
            line.setAmount(amount);
        }
	 }
	 
	 private float roundTwoDecimals(float d) {
     DecimalFormat twoDForm = new DecimalFormat("#.##");
     	return Float.parseFloat(twoDForm.format(d));
	 }
}
