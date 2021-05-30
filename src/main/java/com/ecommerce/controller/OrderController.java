package com.ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.model.request.OrderRequest;
import com.ecommerce.model.response.CartResponse;
import com.ecommerce.model.response.OrderResponse;
import com.ecommerce.service.CartService;
import com.ecommerce.service.OrderService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {
	
	private final OrderService orderService;
	private final CartService cartService;
	@RequestMapping("/checkout")
	public ModelAndView checkout(ModelAndView model) {
		CartResponse cart = cartService.fetchCart();
		if(cart == null ) {
			model = new ModelAndView("cart-empty");
		}else {
			model = new ModelAndView("checkout");
			model.addObject("cart", cart);
		}
		OrderRequest orderRequest =new OrderRequest();
		model.addObject("order", orderRequest);
		return model;
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public String postOrder(@ModelAttribute("order")OrderRequest orderRequest) {
       orderService.postOrder(orderRequest);
       return "redirect:/";
    }
}
