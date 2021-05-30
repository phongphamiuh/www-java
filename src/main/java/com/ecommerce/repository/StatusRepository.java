package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Long>{
	Status findByName(String name);
}
