package com.ecommerce.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce.exception.BadRequestException;
import com.ecommerce.model.entity.User;
import com.ecommerce.repository.UserRepository;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(userName);
		if (user == null) {
		    throw new BadRequestException("Email không tìm thấy");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				getAuthorities(user));
	}

	private static Collection<? extends GrantedAuthority> getAuthorities(User user) {	
		String[] userRoles = user.getRoles()
				.stream()
				.map((role) -> role.getName())
				.toArray(String[]::new);
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
		return authorities;
//		 Set<Role> roles = user.getRoles();
//		    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//		     
//		    for (Role role : roles) {
//		        authorities.add(new SimpleGrantedAuthority(role.getName()));
//		    }
	}
}
