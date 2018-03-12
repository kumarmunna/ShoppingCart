package com.shoppingcart.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shoppingcart.Beans.UserDetailsBean;
import com.shoppingcart.DAO.UserRegistrationDao;
import com.shoppingcart.DAO.impl.HibernateDaoImpl;
import com.shoppingcart.Model.UserDetails;
import com.shoppingcart.Validator.UserRegistrationValidator;

@Controller
public class UserRegistrationController {

	@Autowired
	UserRegistrationValidator userValidator;
	
	@Autowired
	UserRegistrationDao userDao;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	      binder.addValidators(userValidator);
	}
	
	/**
	 * This method is used to redirect user registration view page from request.
	 * 
	 * @return
	 */
	@RequestMapping("register")
	public ModelAndView userViewRegistration(){

		ModelAndView mv = new ModelAndView();
		mv.setViewName("UserRegistration");
		UserDetailsBean userBean = new UserDetailsBean();
		mv.addObject("userdetails",userBean);
		return mv;
	}
	
	@RequestMapping("/userRegistration")
	public ModelAndView RegisterUser(@ModelAttribute("userdetails") @Validated UserDetailsBean userBean,
			BindingResult result, Model model){
		ModelAndView mv  = new ModelAndView();
		if(result.hasErrors()){
			mv.setViewName("UserRegistration");
			return mv;
		}
		UserDetails details = new UserDetails();
		details.setName(userBean.getName());
		details.setAddress(userBean.getAddress());
		details.setPhone(userBean.getPhone());
		details.setEmail(userBean.getEmail());
		details.setUsername(userBean.getUsername());
		details.setPassword(userBean.getPassword());
		details.setActive(true);
		details.setUserrole("USER");
		
		userDao.saveUser(details);
		mv.setViewName("OrderConfirmation");
		return mv;
	}
}
