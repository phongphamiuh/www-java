package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

}
