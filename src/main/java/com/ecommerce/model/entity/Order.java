package com.ecommerce.model.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "orders")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "user")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String shipName;
	
	private String phone;
	
	private String city;
	
	private String district;
	
	private String street;
	
	private String shipAddrees;
	
	private Float totalPrice;
	
	private Float totalCargoPrice;
	
	private LocalDate createDate;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

	
	@ManyToOne(optional = false)
    @JoinColumn(name = "status_id")
    private Status status;
	
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetailList;
    
    

}
