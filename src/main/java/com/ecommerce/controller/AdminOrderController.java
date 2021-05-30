package com.ecommerce.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.ecommerce.model.entity.Order;
import com.ecommerce.service.OrderService;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/admin/order")
@AllArgsConstructor
public class AdminOrderController {
	private final OrderService orderService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String findPaginated(@RequestParam(value = "page") Integer pageNo,
			@RequestParam(value="status",required = false)String status,
		    @RequestParam(value = "sortField" ,required = false,defaultValue = "createDate") String sortField,
		    @RequestParam(value = "sortDir" ,required = false,defaultValue = "asc") String sortDir,
		    Model model) {
		    int pageSize = 5;
		    
		    Page < Order > page = orderService.getAll(pageNo, pageSize, sortField, sortDir,status);
		    
		    List<Order> list = page.getContent();
		    
		    model.addAttribute("sortField", sortField);
		    model.addAttribute("sortDir", sortDir);
		    model.addAttribute("status",status);		 
		    model.addAttribute("orders", page);
		    
		    return "admin-order-list";
	}
}
