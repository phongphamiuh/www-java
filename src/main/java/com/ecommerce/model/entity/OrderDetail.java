package com.ecommerce.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Table(name = "order_detail")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "order")
public class OrderDetail {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	
	@ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
	
	@ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
	
    @Column(name = "amount")
    private Integer amount;
    
    
}
