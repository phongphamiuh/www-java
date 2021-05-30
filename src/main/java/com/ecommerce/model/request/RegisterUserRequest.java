package com.ecommerce.model.request;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.ecommerce.validator.PasswordMatches;

import lombok.Data;

@Data
@PasswordMatches
public class RegisterUserRequest {
	
	@NotEmpty(message = "Email không được để trống")
	@Email(message = "Email phải đúng định dạng")
    private String email;

	@NotEmpty(message = "Password không được để trống")
    @Size(min = 6, max = 20,message = "Password từ 6 tới 20 ký tự")
    private String password;

    @NotEmpty(message = "Kiểm tra password không được để trống")
    @Size(min = 6, max = 20,message = "Password từ 6 tới 20 ký tự")
    private String passwordRepeat;
    
    @NotBlank(message = "Tên không được để trống")
    @Size(min = 6, max = 20,message = "Tên từ 6 tới 20 ký tự")
    private String name;
}
