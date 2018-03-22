package com.shoppingcart.RestControllerDemo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingcart.Model.LoginBean;

@RestController
public class HelloWorldRestController {

	@RequestMapping("santosh")
	public String welcom() {
		return "welcome Bro";
	}
	@RequestMapping(value="/santosh/{name}", method = RequestMethod.GET)
	@ResponseBody
	public LoginBean getName(@PathVariable String name) {
		
		LoginBean bean  = new LoginBean();
		bean.setUsername("santosh");
		bean.setPassword("password");
		return bean;
	};
}
