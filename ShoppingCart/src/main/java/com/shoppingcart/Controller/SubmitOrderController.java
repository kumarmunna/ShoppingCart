package com.shoppingcart.Controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shoppingcart.Beans.CartInfoBean;
import com.shoppingcart.Beans.UserDetailsBean;
import com.shoppingcart.DAO.OrdersDao;
import com.shoppingcart.DAO.Orders_DetailsDao;
import com.shoppingcart.DAO.impl.HibernateDaoImpl;
import com.shoppingcart.Model.Orders;

@Controller
public class SubmitOrderController {

	@Autowired
	OrdersDao ordersDao;
	@Autowired
	Orders_DetailsDao order_detailsDao;
	
	
	@RequestMapping("/submitOrder")
	public ModelAndView submitOrderFromSession(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		//Check custormer existance 
		boolean userStatus = getUserStatus(request);
		if(!userStatus){
			//User is not registered... redirect to user registration page/Login page. 
			mv.setViewName("redirect:/register");
		}else{
			HttpSession session = request.getSession();
			Set<CartInfoBean> cartInfo = (Set<CartInfoBean>)  session.getAttribute("cartInfo");
			order_detailsDao.saveOrder(cartInfo);
			mv.setViewName("OrderConfirmation");
		}
		return mv;
	}
	
	public boolean getUserStatus(HttpServletRequest request){
		
		boolean userStatus = false;
		HttpSession session = request.getSession();
		String usertype = (String) session.getAttribute("userstatus");
		if(usertype != null && usertype.equalsIgnoreCase("auth")){
			userStatus = true;
		}
		return userStatus;
	}
}
