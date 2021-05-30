package com.ecommerce.model.response;


import java.util.List;
import com.ecommerce.model.dto.OrderDetailDto;

import lombok.Data;

@Data
public class OrderResponse {
	
	private Integer id;
	
	private String shipName;
	
	private String phone;
	
	private String city;
	
	private String district;
	
	private String street;
	
	private String shipAddrees;
	
    private List<OrderDetailDto> orderDetailList;
}
