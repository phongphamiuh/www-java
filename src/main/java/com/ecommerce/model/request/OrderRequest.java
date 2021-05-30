package com.ecommerce.model.request;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class OrderRequest {
	@NotBlank
    @Size(min = 3, max = 52)
    @Pattern(regexp = "^[a-zA-Z\\s]+$")
    private String shipName;

    @NotBlank
    @Size(min = 11, max = 12)
    @Pattern(regexp = "[0-9]+")
    private String phone;
    
    @NotBlank
    @Size(min = 3, max = 100)
    @Pattern(regexp = "^[a-zA-Z\\s]+$")
    private String city;

    @NotBlank
    @Size(min = 3, max = 40)
    @Pattern(regexp = "^[a-zA-Z\\s]+$")
    private String district;

    @NotBlank
    @Size(min = 5, max = 6)
    @Pattern(regexp = "^[0-9]*$")
    private String street;
    
    @NotBlank
    @Size(min = 3, max = 240)
    @Pattern(regexp = "[0-9a-zA-Z #,-]+")
    private String shipAddress;
    
}
