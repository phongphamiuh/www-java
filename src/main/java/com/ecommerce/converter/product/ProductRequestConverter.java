package com.ecommerce.converter.product;

import java.util.function.Function;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.model.entity.Product;
import com.ecommerce.model.request.ProductRequest;

@Component
public class ProductRequestConverter implements Function<ProductRequest, Product>{

	@Override
	public Product apply(ProductRequest productRequest) {
		Product product = new Product();
		product.setBrand(productRequest.getBrand());
		product.setName(productRequest.getName());
		product.setPrice(productRequest.getPrice());
		product.setStock(productRequest.getStock());
		product.setOnSalePrice(productRequest.getOnSalePrice());
		product.setCargoPrice(productRequest.getCargoPrice());
		product.setDescription(productRequest.getDescription());
		product.setImage(productRequest.getImage());
		return product;
	}
}
