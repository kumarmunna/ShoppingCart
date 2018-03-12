package com.shoppingcart.Validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.shoppingcart.Beans.UserDetailsBean;

public class UserRegistrationValidator implements Validator{

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserDetailsBean.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmpty(errors, "username", "user.name.empty");
		ValidationUtils.rejectIfEmpty(errors, "password", "user.password.empty");
		ValidationUtils.rejectIfEmpty(errors, "name", "user.name.empty");
		ValidationUtils.rejectIfEmpty(errors, "address", "user.address.empty");
		ValidationUtils.rejectIfEmpty(errors, "phone", "user.phone.empty");
		ValidationUtils.rejectIfEmpty(errors, "email", "user.email.empty");
		
	}

}
