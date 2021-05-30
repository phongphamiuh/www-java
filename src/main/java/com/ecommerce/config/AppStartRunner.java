//package com.ecommerce.config;
//
//import java.util.Arrays;
//
//import org.springframework.beans.factory.config.BeanPostProcessor;
//import org.springframework.context.ApplicationEvent;
//import org.springframework.context.ApplicationListener;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import com.ecommerce.exception.BadRequestException;
//import com.ecommerce.model.entity.Role;
//import com.ecommerce.model.entity.User;
//import com.ecommerce.repository.RoleRepository;
//import com.ecommerce.repository.UserRepository;
//
//import lombok.AllArgsConstructor;
//
//@Component
//@AllArgsConstructor
//public class AppStartRunner implements ApplicationListener<ApplicationEvent>{
//
//	private final UserRepository userRepository;
//	private final PasswordEncoder crypt;
//	private final RoleRepository roleRepository;
//	
//	@Override
//	public void onApplicationEvent(ApplicationEvent event) {
//		User user = new User();
//		user.setName("phong");
//        user.setEmail("phongadmin@gmail.com");
//        user.setPassword(crypt.encode("phongpham"));
//        Role role =roleRepository.findByName("ROLE_ADMIN")
//        		.orElseThrow(()-> new BadRequestException("Not found"));
//        user.setRoles(Arrays.asList(role));
//        userRepository.save(user);
//	}
//
//	
//}
