package com.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ecommerce.model.entity.Order;
import com.ecommerce.model.entity.Product;
import com.ecommerce.model.entity.User;

public interface OrderRepository extends JpaRepository<Order, Long>,JpaSpecificationExecutor<Order>{
	
//	List<Order> findAllByUserOrderByDateDesc(User user, Pageable pageable);
//	
//    Optional<Integer> countAllByUser(User user);
}
