package com.ecommerce.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
public class Role {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
 
    private String name;
    
    private String description;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    @ToString.Exclude
    private List < User > users;

	public Role(String name) {
		super();
		this.name = name;
	}
    
    
}
