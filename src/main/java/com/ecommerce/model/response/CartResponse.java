package com.ecommerce.model.response;

import java.util.List;

import com.ecommerce.model.dto.CartItemDto;

import lombok.Data;

@Data
public class CartResponse {
    private Float totalCartPrice;
    private Float totalCargoPrice;
    private Float totalPrice;
    private List<CartItemDto> cartItems;
    
}
