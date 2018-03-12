package com.shoppingcart.Validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.shoppingcart.Beans.ProductsBean;

public class ProductValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return ProductsBean.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
		ValidationUtils.rejectIfEmpty(errors, "code", "product.code.empty");
		ValidationUtils.rejectIfEmpty(errors, "name", "product.name.empty");
		ValidationUtils.rejectIfEmpty(errors, "price", "prduct.price.empty");
		
	}

}
