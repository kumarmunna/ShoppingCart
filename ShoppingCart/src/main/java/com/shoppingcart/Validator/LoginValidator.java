package com.shoppingcart.Validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.shoppingcart.Model.LoginBean;


public class LoginValidator implements Validator
{

	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		System.out.println(" ------------------------ in supports login validator ----------------,,,"+ arg0);
		return LoginBean.class.equals(arg0);
	}

	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		System.out.println(" ------------------------ in validator ----------------,,,");
		ValidationUtils.rejectIfEmpty(errors, "username", "user.name.empty");
		ValidationUtils.rejectIfEmpty(errors, "password", "user.password.empty");
		/*ValidationUtils.rejectIfEmpty(errors, "exist", "user.exist.empty");
		ValidationUtils.rejectIfEmpty(errors, "notexist", "user.notexist.empty");
		LoginBean lb = (LoginBean) obj;
		if(!lb.isExist()){
			errors.rejectValue("exist", "user.exist.empty");
		}*/
	}

}
