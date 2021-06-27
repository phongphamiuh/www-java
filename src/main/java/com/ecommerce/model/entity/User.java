package com.ecommerce.model.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String email;
	
	private String password;
	
	private String name;
	
	private String phone;
	
	private String city;
	
	private String district;
	
	private String street;
	
	private String shipAddress;
	
	private boolean emailVerified;
	
	private LocalDate registrationDate;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@ToString.Exclude
    	private Cart cart;
	
	@ManyToMany(cascade = CascadeType.MERGE)
	@JsonIgnore
	@ToString.Exclude
	@JoinTable(name = "user_role", joinColumns = {
			@JoinColumn(name = "USER_ID", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID") })
	private List<Role> roles;
}
