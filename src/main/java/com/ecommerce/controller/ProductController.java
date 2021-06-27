package com.ecommerce.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.model.entity.Product;
import com.ecommerce.model.entity.ProductCategory;
import com.ecommerce.model.response.ProductResponse;
import com.ecommerce.service.CategoryService;
import com.ecommerce.service.ProductService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("products")
@AllArgsConstructor
public class ProductController {
	
	private final ProductService productService;
	private final CategoryService categoryService;
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public String get(@PathVariable("id")Long id,Model model){
		ProductResponse productResponse = productService.get(id);
		model.addAttribute("product", productResponse);
		return "product-detail";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String findPaginated(@RequestParam(value = "page") Integer pageNo,
			@RequestParam(value="categoryName")String categoryName,
		    @RequestParam(value = "sortField" ,required = false) String sortField,
		    @RequestParam(value = "sortDir" ,required = false) String sortDir,
		    Model model) {
		    int pageSize = 12;

		    Page < Product > page = productService.findPaginated(pageNo, pageSize, sortField, sortDir,categoryName);
		    List<ProductCategory> categories = categoryService.getAll();
		    
		    List < Product > productList = page.getContent();
		    productList.forEach(product ->{
		    	System.out.println(product);
		    });
//	
//		    model.addAttribute("currentPage", pageNo);
//		    model.addAttribute("totalPages", page.getTotalPages());
//		    
//		    model.addAttribute("prePage", page.previousPageable().hasPrevious());
//		    model.addAttribute("totalItems", page.getTotalElements());
		    model.addAttribute("sortField", sortField);
		    model.addAttribute("sortDir", sortDir);
		    model.addAttribute("categoryName",categoryName);
		    model.addAttribute("categories", categories);
//		    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		    
		    model.addAttribute("products", page);
		    return "product-list";
	}
}
