package com.ecommerce.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.model.entity.ProductCategory;
import com.ecommerce.service.CategoryService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
	
	private final CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<List<ProductCategory>> getAll(){
		return ResponseEntity.ok(categoryService.getAll());
	}
}
