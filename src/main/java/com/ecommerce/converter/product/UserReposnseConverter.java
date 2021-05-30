package com.ecommerce.converter.product;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import com.ecommerce.model.dto.RoleDto;
import com.ecommerce.model.entity.User;
import com.ecommerce.model.response.UserResponse;
@Component
public class UserReposnseConverter implements Function<User, UserResponse>{
	@Override
	public UserResponse apply(User user) {
		
		UserResponse userResponse = new UserResponse();
		userResponse.setId(user.getId());
		userResponse.setEmail(user.getEmail());
		userResponse.setName(user.getName());
		userResponse.setPhone(user.getPhone());
		userResponse.setCity(user.getCity());
		userResponse.setDistrict(user.getDistrict());
		userResponse.setShipAddress(user.getShipAddress());
		userResponse.setStreet(user.getStreet());
		userResponse.setRegistrationDate(user.getRegistrationDate());
		
		userResponse.setRoles(user.getRoles()
                .stream()
                .map(role -> RoleDto
                        .builder()
                        .name(role.getName())
                        .description(role.getDescription())
                        .build())
                .collect(Collectors.toList()));
		
		return userResponse;
	}
}
