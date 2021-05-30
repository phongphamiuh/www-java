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

@Table
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "cart")
public class CartItem {
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
	    private Long id;
	
	    @ManyToOne
	    @JoinColumn(name = "cart_id")
	    private Cart cart;
	
	    @ManyToOne
	    @JoinColumn(name = "product")
	    private Product product;
	
	    @Column(name = "amount")
	    private Integer amount;
}
