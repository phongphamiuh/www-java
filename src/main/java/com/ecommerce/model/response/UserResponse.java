package com.ecommerce.model.response;

import java.time.LocalDate;
import java.util.List;

import com.ecommerce.model.dto.RoleDto;

import lombok.Data;
@Data
public class UserResponse {
	
	private Integer id;
	
	private String email;
	
	private String name;
	
	private String phone;
	
	private String city;
	
	private String district;
	
	private String street;
	
	private String shipAddress;
	
	private LocalDate registrationDate;

	private List<RoleDto> roles;
}

