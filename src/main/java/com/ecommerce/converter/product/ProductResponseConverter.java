package com.ecommerce.converter.product;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.ecommerce.model.entity.Product;
import com.ecommerce.model.response.ProductResponse;

@Component
public class ProductResponseConverter implements Function<Product, ProductResponse>{

	@Override
	public ProductResponse apply(Product pr) {
		
		ProductResponse productRepsonse = new ProductResponse();
		productRepsonse.setId(pr.getId());
		productRepsonse.setBrand(pr.getBrand());
		productRepsonse.setCategoryName(pr.getProductCategory().getName());
		productRepsonse.setName(pr.getName());
		productRepsonse.setPrice(pr.getPrice());
		productRepsonse.setStock(pr.getStock());
		productRepsonse.setOnSalePrice(pr.getOnSalePrice());
		productRepsonse.setCargoPrice(pr.getCargoPrice());
	
		return productRepsonse;
	}

}
