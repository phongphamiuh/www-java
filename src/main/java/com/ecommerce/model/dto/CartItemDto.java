package com.ecommerce.model.dto;

import com.ecommerce.model.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CartItemDto {
	
	private Long id;

    private String name;

    private Float price;
    
    private Float onSalePrice;
    
    private Float cargoPrice;
    
    private Integer stock;
    
    private Long productId;
    
    private Integer amount;
    
    
    public CartItemDto(Product product,Integer amount) {
    	this.name = product.getName();
    	this.price = product.getPrice();
    	this.onSalePrice = product.getOnSalePrice();
    	this.stock = product.getStock();
    	this.productId = product.getId();
    	this.cargoPrice = product.getCargoPrice();
    	this.amount = amount;
    }
    
    public CartItemDto(){
    	
    }
    
    public Float getLineTotal(){
    	return this.onSalePrice*this.amount;
    }
}
