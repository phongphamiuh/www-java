package com.ecommerce.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommerce.model.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>,JpaSpecificationExecutor<Product>{
	
	@Query("from Product p join p.productCategory c where c.name=:name")
	Page<Product> findAllByCategoryName(@Param("name")String name, Pageable pageable);
	
	@Query(value="select * from product p order by p.onSalePrice asc limit 8",nativeQuery = true)
	List<Product> findAllWithOnSale();
	
	@Query(value="select * from product p order by p.stock asc limit 8",nativeQuery = true)
	List<Product> findAllWithHotSale();
	
	@Query(value = "select * from product p join product_category c on p.category_id=c.id where c.name=:name limit 8 ",nativeQuery = true)
	List<Product> findAllByCategoryName(@Param("name") String name);
}	
