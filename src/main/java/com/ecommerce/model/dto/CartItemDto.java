package com.ecommerce.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CartItemDto {
	
	private Long id;

    private String name;

    private Float price;
    
    private Float onSalePrice;
    
    private Integer stock;

    private Integer amount;
    
    private Long productId;

}
