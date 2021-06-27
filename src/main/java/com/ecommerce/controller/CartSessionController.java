package com.ecommerce.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.model.dto.CartItemDto;
import com.ecommerce.model.entity.Product;
import com.ecommerce.model.response.CartResponse;
import com.ecommerce.model.response.CartSession;
import com.ecommerce.service.CartService;
import com.ecommerce.service.ProductService;
import com.ecommerce.session.CartSessionUntil;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class CartSessionController {
	
	private final ProductService productService;
	@RequestMapping("/add")
	public String post(@RequestParam("id")Long id,
			@RequestParam("amount")Integer amount,
			Model model,HttpServletRequest httpRequest) {
		
		
		Product product = productService.findById(id);
		CartSession cartSession = CartSessionUntil.getCartInSession(httpRequest);
		CartItemDto cartItem = new CartItemDto(product, amount);
		cartSession.addProduct(cartItem, amount);
		
		return "redirect:/cart";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView get(ModelAndView model,HttpServletRequest httpRequest) {
		CartSession cart = CartSessionUntil.getCartInSession(httpRequest);
		if(cart.getCartItems().size() == 0 ) {
			model = new ModelAndView("cart-empty");
		}else {
			model = new ModelAndView("cart");
			model.addObject("cart", cart);
		}
		return model;
	}
	
	 @RequestMapping(value = "/remove")
	 public String removeFromCart(@RequestParam("id")Long id,HttpServletRequest httpRequest) {
		 CartSession cart = CartSessionUntil.getCartInSession(httpRequest);
		 cart.removeProduct(id);
	     return "redirect:/cart";
	 }
	 
   @RequestMapping(value = "/update/amount")
   public String updateAmount(@RequestParam("id")Long id,@RequestParam("amount")Integer amount,HttpServletRequest httpRequest) {
	   CartSession cart = CartSessionUntil.getCartInSession(httpRequest);
	   cart.updateQuantity(id, amount);
       return "redirect:/cart";
   }

}
