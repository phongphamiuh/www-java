package com.ecommerce.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.converter.product.UserReposnseConverter;
import com.ecommerce.model.entity.Product;
import com.ecommerce.model.entity.ProductCategory;
import com.ecommerce.model.entity.User;
import com.ecommerce.service.UserService;

import lombok.AllArgsConstructor;


@Controller
@RequestMapping("/moderator/user")
@AllArgsConstructor
public class ModeratorUserController {
	private final UserService userService;
	private final UserReposnseConverter userResponseConverter;
		
	@RequestMapping(method = RequestMethod.GET)
	public String findPaginated(@RequestParam(value = "page",defaultValue = "1") Integer pageNo,
		    @RequestParam(value = "sortField" ,required = false,defaultValue = "id") String sortField,
		    @RequestParam(value = "sortDir" ,required = false,defaultValue = "asc") String sortDir,
		    Model model) {
		    int pageSize = 5;

		    Page < User > page = userService.getAll(pageNo, pageSize, sortField, sortDir);
		    
		    List < User > userList = page.getContent();
		    
		    userList.stream()
		    	.map(user->userResponseConverter.apply(user))
		    	.collect(Collectors.toList());
		    	
		    model.addAttribute("sortField", sortField);
		    model.addAttribute("sortDir", sortDir);
//			    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		    model.addAttribute("users", page);
		    
		    return "moderator-user-list";
	}
}
