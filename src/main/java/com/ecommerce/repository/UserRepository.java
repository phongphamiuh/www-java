package com.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	 User findByEmail(String email);
	
}
