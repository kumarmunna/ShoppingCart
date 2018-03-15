package com.shoppingcart.Controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shoppingcart.Beans.CartInfoBean;
import com.shoppingcart.Beans.OrderDetailsBean;
import com.shoppingcart.Beans.OrderListBean;
import com.shoppingcart.Beans.UserDetailsBean;
import com.shoppingcart.DAO.OrdersDao;
import com.shoppingcart.DAO.Orders_DetailsDao;
import com.shoppingcart.DAO.impl.HibernateDaoImpl;
import com.shoppingcart.Model.Orders;

@Controller
public class OrderController {

	@Autowired
	OrdersDao ordersDao;
	@Autowired
	Orders_DetailsDao order_detailsDao;
	
	
	@RequestMapping("/submitOrder")
	public ModelAndView submitOrderFromSession(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		//Check custormer existance 
		boolean userStatus = getUserStatus(request);
		System.out.println(" user status; "+ userStatus);
		if(!userStatus){
			//User is not registered... redirect to user registration page/Login page. 
			mv.setViewName("redirect:/register");
		}else{
			HttpSession session = request.getSession();
			Set<CartInfoBean> cartInfo = (Set<CartInfoBean>)  session.getAttribute("cartInfo");
			UserDetailsBean userdetails = (UserDetailsBean) session.getAttribute("userdetails");
			order_detailsDao.saveOrder(cartInfo,userdetails);
			mv.setViewName("OrderConfirmation");
		}
		return mv;
	}
	
	@RequestMapping("getOrderList")
	public ModelAndView getOrderListFromDatabase(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("OrderList");
		List<OrderListBean> orderlist = order_detailsDao.orderList();
		mv.addObject("orderlist",orderlist);
		return mv;
	}
	
	@RequestMapping("getOrderDetails")
	public ModelAndView getOrderDetailsByOrderId(@RequestParam("orderid") int orderid){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("OrderDetailList");
		List<OrderDetailsBean> orderlist = order_detailsDao.getOrderByOrderId(orderid);
		System.out.println(" order ist: "+ orderlist);
		mv.addObject("orderdetaillist",orderlist);
		return mv;
	}
	
	public boolean getUserStatus(HttpServletRequest request){
		
		boolean userStatus = false;
		HttpSession session = request.getSession();
		System.out.println(" in get user status ---->>");
		String usertype = (String) session.getAttribute("userstatus");
		System.out.println(" in get user type ---->>"+ usertype);
		if(usertype != null && usertype.equalsIgnoreCase("auth")){
			userStatus = true;
		}
		return userStatus;
	}
	
	
}
