package com.ecommerce.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ecommerce.model.request.RegisterUserRequest;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object>{
	
	@Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        if (obj instanceof RegisterUserRequest) {
            RegisterUserRequest registerUserRequest = (RegisterUserRequest) obj;
            return registerUserRequest.getPassword()
            		.equals(registerUserRequest.getPasswordRepeat());
        } 
        return false;

    }
}
