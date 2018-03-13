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
	 * This method is used to get the all items saved in cart to display all product
	 * on cart page based on the repriced product.
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getCart")
	public ModelAndView getCartDetails(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		Set<CartInfoBean> cartInfoset = getCartInfo(session);
		Set<CartInfoBean> repricingCartData = getRepricedCartData(cartInfoset);
		if(cartInfoset != null){
			mv.addObject("cartInfo",repricingCartData);
		}
		mv.setViewName("CartPage");
		return mv;
		
	}
	/**
	 * This method is used to get the repriced product saved in cart.
	 * It checks the saved cart product price and if price is not equals with saved product in cart then
	 * it get the current price from database and update in the cart bean.
	 * 
	 * @param cartInfo
	 * @return
	 */
	public Set<CartInfoBean> getRepricedCartData(Set<CartInfoBean> cartInfo){
		
			Set<CartInfoBean> cartdata = new HashSet<CartInfoBean>();
			for(CartInfoBean cartbean : cartInfo){
				String prodCode = cartbean.getCode();
				ProductsBean bean = getProductDetails(prodCode);
				boolean check = checkPrice(bean,cartbean);
				if(!check){
					cartbean.setPrice(bean.getPrice());
					cartbean.setSubtotal(getPriceOfQuantity(bean.getPrice(), cartbean.getQuantity()));
				}
				cartdata.add(cartbean);
			}
		return cartdata;
	}
	
	/*
	 * Method is used to get the subtotal price of product with the multiplication with
	 * quantity selected.
	 */
	public int getPriceOfQuantity(double price, int qty){
		int qntprice = ((Double)price).intValue() * qty;
		return qntprice;
	}
	
	/**
	 * Method used to check the saved cart product price is equal or not in database.
	 * 
	 * @param bean
	 * @param cart
	 * @return
	 */
	public boolean checkPrice(ProductsBean bean,CartInfoBean cart){
		boolean check = false;
		if(((Double)bean.getPrice()).intValue() == ((Double)cart.getPrice()).intValue()){
			check = true;
		}else{
			check = false;
		}
		return check;
	}
	/**
	 * Method checks the cart info data is available or not.  
	 * If not then it creat new set object and return that.
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
	 * Method used to get the product details based on product code.
	 * 
	 * @param productCode
	 * @return
	 */
	public ProductsBean getProductDetails(String productCode){
		ProductsBean proBean = productsDao.getProductsDetail(productCode);
		return proBean;
	}
}
