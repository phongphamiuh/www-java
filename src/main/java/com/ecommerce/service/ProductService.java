package com.ecommerce.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommerce.model.entity.Product;
import com.ecommerce.model.request.ProductRequest;
import com.ecommerce.model.response.ProductResponse;

public interface ProductService {
	Product saveProduct(ProductRequest productRequest);
	
	List<ProductResponse> getAll();
	
	ProductResponse get(Long id);
	
	Product findById(Long id);
	
	Page<Product> findPaginated(int page, int pageSize, String sortField, String sortDirection,String categoryName);
	
	void updateProduct(Product product);
	
	void deleteProduct(Long id);
	

	List<ProductResponse> findAllByOnSale();
	
	List<ProductResponse> findAllByHotSale();

	List<ProductResponse> findAllByCategory(String name);
}
