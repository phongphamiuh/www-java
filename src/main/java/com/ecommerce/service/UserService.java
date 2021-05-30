package com.ecommerce.service;

import org.springframework.data.domain.Page;

import com.ecommerce.model.entity.Product;
import com.ecommerce.model.entity.User;
import com.ecommerce.model.request.RegisterUserRequest;
import com.ecommerce.model.response.UserResponse;

public interface UserService {
	User findByEmail(String email);

    User save(RegisterUserRequest registerUserRequest);
    
    User getUser();
    
    User saveUser(User user);
    
    Page<User> getAll(int page, int pageSize, String sortField, String sortDirection);
}
