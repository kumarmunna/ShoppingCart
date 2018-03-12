package com.shoppingcart.Controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
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
import com.shoppingcart.Beans.ProductsBean;
import com.shoppingcart.DAO.OrdersDao;
import com.shoppingcart.DAO.ProductsDao;
import com.shoppingcart.DAO.impl.HibernateDaoImpl;

@Controller
public class CartController {

	@Autowired
	private OrdersDao ordersDao;
	@Autowired
	private ProductsDao productsDao;
	@Autowired
	HibernateDaoImpl hibernateDaoImpl;
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addInCart")
	public ModelAndView getCartItems(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("CartPage");
		
		HttpSession session = request.getSession();
		Set<CartInfoBean> cartInfo = getCartInfo(session);
		String productCode = request.getParameter("productCode").toString();
		ProductsBean productsBean = getProductDetails(productCode);
		
		
		CartInfoBean bean = new CartInfoBean();
		bean.setCode(productCode);
		bean.setName(productsBean.getName());
		bean.setPrice(productsBean.getPrice());
		bean.setFilename(productsBean.getFilename());
		bean.setQuantity(1);
		bean.setSubtotal(productsBean.getPrice());
		if(!cartInfo.contains(bean)){
			cartInfo.add(bean);
		}
		session.setAttribute("cartInfo", cartInfo);
		mv.addObject("cartInfo", cartInfo);
		return mv;
	}
	
	@RequestMapping("/removeFromCart")
	public ModelAndView remmoveProductFromCart(@RequestParam("productCode") String productCode, HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("CartPage");
		HttpSession session = request.getSession();
		Set<CartInfoBean> cartInfo = (Set<CartInfoBean>) session.getAttribute("cartInfo");
		for (Iterator iterator = cartInfo.iterator(); iterator.hasNext();) {
			CartInfoBean cartInfoBean = (CartInfoBean) iterator.next();
			if(cartInfoBean.getCode().equalsIgnoreCase(productCode)){
				iterator.remove();
			}
		}
		session.setAttribute("cartInfo", cartInfo);
		mv.addObject("cartInfo", cartInfo);
		
		return mv;
	}
	
	@RequestMapping("updateQuantity")
	public ModelAndView updateQuantityInCart(@RequestParam("code") String prdCode, @RequestParam("qty") int qty, HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("CartPage");
		HttpSession session = request.getSession();
		Set<CartInfoBean> cartInfo = (Set<CartInfoBean>) session.getAttribute("cartInfo");
		Set<CartInfoBean> modifiedCartInfo = new HashSet<CartInfoBean>();
		for (Iterator iterator = cartInfo.iterator(); iterator.hasNext();) {
			CartInfoBean cartInfoBean = (CartInfoBean) iterator.next();
			if(cartInfoBean.getCode().equalsIgnoreCase(prdCode)){
				cartInfoBean.setQuantity(qty);
				System.out.println(" Comming price: "+ cartInfoBean.getPrice() + " qty: "+ qty);
				Double d = (Double) productsDao.getPrice(prdCode);
				System.out.println(" updateed price: "+ d + " qty: "+ cartInfoBean.getQuantity());
				cartInfoBean.setSubtotal(d.intValue() * qty);
				modifiedCartInfo.add(cartInfoBean);				
			}else{
				System.out.println(" not match qty: "+ cartInfoBean.getQuantity());
				modifiedCartInfo.add(cartInfoBean);
			}
		}
		session.setAttribute("cartInfo", modifiedCartInfo);
		mv.addObject("cartInfo", modifiedCartInfo);
		
		return mv;
	}
	
	/**
	 * 
	 * @param session
	 * @return
	 */
	public Set<CartInfoBean> getCartInfo(HttpSession session){
		Set<CartInfoBean> cartInfo = null;
		if(session.getAttribute("cartInfo") != null){
			cartInfo = (Set<CartInfoBean>) session.getAttribute("cartInfo");
		}else{
			cartInfo = new HashSet<CartInfoBean>();
		}
		return cartInfo;
	}
	/**
	 * 
	 * @param productCode
	 * @return
	 */
	public ProductsBean getProductDetails(String productCode){
		ProductsBean proBean = productsDao.getProductsDetail(productCode);
		return proBean;
	}
}
