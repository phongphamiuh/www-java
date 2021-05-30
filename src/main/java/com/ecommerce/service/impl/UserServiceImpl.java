package com.ecommerce.service.impl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.converter.product.UserReposnseConverter;
import com.ecommerce.exception.BadRequestException;
import com.ecommerce.model.entity.Product;
import com.ecommerce.model.entity.Role;
import com.ecommerce.model.entity.User;
import com.ecommerce.model.request.RegisterUserRequest;
import com.ecommerce.repository.RoleRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.UserService;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
	
	
	private final UserRepository userRepository;
	
	private final RoleRepository roleRepository;
	
	private final PasswordEncoder crypt;
	
	private final UserReposnseConverter userResponseConverter;
	
	@Override
	public User findByEmail(String email) {
		User user=userRepository.findByEmail(email);	
		return user;
	}

	@Override
	public User save(RegisterUserRequest registerUserRequest) {
		User user = new User();
		user.setName(registerUserRequest.getName());
        user.setEmail(registerUserRequest.getEmail());
        user.setPassword(crypt.encode(registerUserRequest.getPassword()));
        user.setRegistrationDate(LocalDate.now());
        Role role =roleRepository.findByName("ROLE_USER")
        		.orElseThrow(()-> new BadRequestException("Not found"));
        user.setRoles(Arrays.asList(role));
        return userRepository.save(user);
	}

	@Override
	public User getUser() {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (Objects.isNull(userName)) {
            throw new AccessDeniedException("Invalid access");
        }

        User user = userRepository.findByEmail(userName);
        if (Objects.isNull(user)) {
            throw new BadRequestException("User not found");
        }
        return user;
	}

	@Override
    public User saveUser(User user) {
        if (Objects.isNull(user)) {
            throw new BadRequestException("Null user");
        }
        return userRepository.save(user);
    }
	
	@Override
	public Page<User> getAll(int page, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : 
			Sort.by(sortField).descending();
		
		PageRequest pageRequest;
        if (Objects.nonNull(sortDirection)) {
            Sort sortRequest = sort;
            if (Objects.isNull(sortRequest)) {
                throw new BadRequestException("Invalid sort parameter");
            }
            pageRequest = PageRequest.of(page -1, pageSize, sortRequest);
        } else {
            pageRequest = PageRequest.of(page -1, pageSize);
        }
        
        
      //  Pageable pageable = PageRequest.of(pageNo - 1, pageSize,Sort.by("onSalePrice").ascending());
       return userRepository.findAll(pageRequest);
	}

}
