package com.ecommerce.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.model.entity.ProductCategory;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.service.CategoryService;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService{
	
	private final CategoryRepository categoryRepository;
	
	@Override
	public List<ProductCategory> getAll() {
		return categoryRepository.findAll();
	}

}
