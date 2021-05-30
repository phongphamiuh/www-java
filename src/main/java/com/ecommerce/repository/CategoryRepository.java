package com.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.entity.ProductCategory;

public interface CategoryRepository extends JpaRepository<ProductCategory, Long>{
	Optional<ProductCategory> findByName(String name);
}
