package com.ecommerce.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecommerce.model.entity.NotificationEmail;
import com.ecommerce.model.request.OrderRequest;
import com.ecommerce.model.response.CartResponse;
import com.ecommerce.model.response.CartSession;
import com.ecommerce.model.response.OrderResponse;
import com.ecommerce.service.CartService;
import com.ecommerce.service.OrderService;
import com.ecommerce.service.impl.MailService;
import com.ecommerce.session.CartSessionUntil;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {
	
	private final OrderService orderService;
//	private final CartService cartService;
	private final MailService mailService;
	
	@RequestMapping("/checkout")
	public ModelAndView checkout(ModelAndView model,HttpServletRequest httpRequest) {
		CartSession cart = CartSessionUntil.getCartInSession(httpRequest);
		if(cart.getCartItems().size() == 0 ) {
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
    public String postOrder(@ModelAttribute("order")OrderRequest orderRequest,HttpServletRequest httpRequest,RedirectAttributes redirect) {
	   CartSession cart = CartSessionUntil.getCartInSession(httpRequest);
	   
       OrderResponse orderResponse = orderService.postOrder(orderRequest,cart);
       
       CartSessionUntil.removeCartInSession(httpRequest);
       CartSessionUntil.storeLastOrderedCartInSession(httpRequest, cart);
       redirect.addAttribute("id", orderResponse.getId());
       return "redirect:/order/success";
    }
	
	@RequestMapping("/success")
	public String checkoutSucess(@RequestParam("id")Long id,Model model) {
		OrderResponse orderResponse = orderService.getOrder(id);
		model.addAttribute("order", orderResponse);
		
		return "checkout-success";
	}
	
	@RequestMapping("/success/confirm")
	public String checkoutConfirm(@RequestParam("id")Long id,Model model,Principal authen) {
		OrderResponse orderResponse = orderService.getOrder(id);
		 mailService.sendMail(new NotificationEmail("Cảm ơn bạn đã đăng ký tài khoản", authen.getName(),
	                "Cảm ơn bạn đã đặt hàng"
	        ));
		//mailService.sendMailWithOrder(new NotificationEmail("Đặt hàng thành công", authen.getName(), null),orderResponse);
		return "redirect:/";
	}
}
