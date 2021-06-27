package com.ecommerce.model.response;


import java.time.LocalDate;
import java.util.List;
import com.ecommerce.model.dto.OrderDetailDto;

import lombok.Data;

@Data
public class OrderResponse {
	
	private Long id;
	
	private String shipName;
	
	private String phone;
	
	private String city;
	
	private String district;
	
	private String street;
	
	private String shipAddrees;
	
	private Float totalPrice;
	
	private LocalDate createDate;
	
    private List<OrderDetailDto> orderDetailList;
}
