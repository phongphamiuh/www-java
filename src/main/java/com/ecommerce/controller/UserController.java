package com.ecommerce.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.converter.product.UserReposnseConverter;
import com.ecommerce.model.entity.Product;
import com.ecommerce.model.entity.User;
import com.ecommerce.model.request.RegisterUserRequest;
import com.ecommerce.service.UserService;

import lombok.AllArgsConstructor;


@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
	private static final Logger logger = 
	        LoggerFactory.getLogger(UserController.class);
	
	
	private final UserService userService;
	private final UserReposnseConverter userResponseConverter;
	

	@ModelAttribute("user")
	public RegisterUserRequest userRegistrationDto() {
		return new RegisterUserRequest();
	}

	@GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        return "registration";
    }
    
    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("user") @Valid RegisterUserRequest registerUserRequest,
        BindingResult result) {
    	System.out.println("Registration Post");
    	logger.info("Registration Post");
        User existing = userService.findByEmail(registerUserRequest.getEmail());
        
        if (existing != null) {
            result.rejectValue("email", null, "Tài khoản đã tồn tại");
        }

        if (result.hasErrors()) {
            return "registration";
        }

        userService.save(registerUserRequest);
        
        return "redirect:/user/registration?success";
    }
    
  
    
}
