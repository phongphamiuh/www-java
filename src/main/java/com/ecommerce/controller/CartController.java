//package com.ecommerce.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.ecommerce.model.response.CartResponse;
//import com.ecommerce.service.CartService;
//import com.ecommerce.service.ProductService;
//
//import lombok.AllArgsConstructor;
//
//@Controller
//@RequestMapping("/cart")
//@AllArgsConstructor
//public class CartController {
//	
//	private final CartService cartService;
//	
//	@RequestMapping("/add")
//	public String post(@RequestParam("id")Long id,
//			@RequestParam("amount")Integer amount,
//			Model model) {
//		System.out.println("Hello");
//		
//		CartResponse cartResponse = cartService.addToCart(id, amount);
//		
//		System.out.println(cartResponse);
//		
//		return "redirect:/cart";
//	}
//	
//	@RequestMapping(method = RequestMethod.GET)
//	public ModelAndView get(ModelAndView model) {
//		CartResponse cart = cartService.fetchCart();
//		if(cart == null ) {
//			model = new ModelAndView("cart-empty");
//		}else {
//			model = new ModelAndView("cart");
//			model.addObject("cart", cart);
//		}
//		return model;
//	}
//	
//	 @RequestMapping(value = "/remove")
//	 public String removeFromCart(@RequestParam("id")Long id) {
//	      cartService.removeFromCart(id);
//	      return "redirect:/cart";
//	 }
//	 
//	@RequestMapping(value = "/increment")
//    public String increaseCartItem(@RequestParam("id")Long id,@RequestParam("amount")Integer amount) {
//        cartService.incrementCartItem(id, amount);
//        return "redirect:/cart";
//    }
//
//	@RequestMapping(value = "/decrement")
//    public String decreaseCartItem(@RequestParam("id")Long id,@RequestParam("amount")Integer amount) {
//		cartService.decrementCartItem(id, amount);
//		 return "redirect:/cart";
//    }
//
//}
