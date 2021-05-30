package com.ecommerce.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ecommerce.model.entity.ProductCategory;
import com.ecommerce.model.response.ProductResponse;
import com.ecommerce.service.CategoryService;
import com.ecommerce.service.ProductService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class HomeController {
	
	private final ProductService productService;
	private final CategoryService categoryService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model) {
		String refri = "Tủ lạnh";
		String air = "Máy điều hòa";
		List<ProductResponse> products = productService.getAll();
		List<ProductResponse> onSales = productService.findAllByOnSale();
		List<ProductResponse> hotSales = productService.findAllByHotSale();
		List<ProductResponse> refris = productService.findAllByCategory("Tủ lạnh");
		List<ProductResponse> airs = productService.findAllByCategory("Máy điều hòa");
		List<ProductCategory> categories = categoryService.getAll();
		
		model.addAttribute("products", products);
		model.addAttribute("onSales", onSales);
		model.addAttribute("hotSales", hotSales);
		model.addAttribute("refris", refris);
		model.addAttribute("airs", airs);
		model.addAttribute("categories", categories);
		return "index";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)  
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {  
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
        if (auth != null){      
           new SecurityContextLogoutHandler().logout(request, response, auth);  
        }  
         return "redirect:/";  
     }  
}
