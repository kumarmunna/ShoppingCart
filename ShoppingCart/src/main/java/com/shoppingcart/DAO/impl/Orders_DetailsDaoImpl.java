package com.shoppingcart.DAO.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import com.shoppingcart.Beans.CartInfoBean;
import com.shoppingcart.Beans.UserDetailsBean;
import com.shoppingcart.DAO.Orders_DetailsDao;
import com.shoppingcart.Model.Orders;
import com.shoppingcart.Model.Orders_Details;

public class Orders_DetailsDaoImpl implements Orders_DetailsDao {

	@Autowired
	HibernateDaoImpl hibernateDaoImpl;

	public void saveOrder(Set<CartInfoBean> cartInfoBeans,UserDetailsBean userDetailsBean) {
		// TODO Auto-generated method stub
		Session session = hibernateDaoImpl.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Orders_Details orders_Details = null;
		System.out.println(" in save order -----email is-"+userDetailsBean.getEmail()+" name: "+ userDetailsBean.getName());
		
		Orders order = new Orders();
		int totalAmntInCart = getTotalAmountInCartItems(cartInfoBeans);
//		System.out.println(" max id-----> "+ order.toString());
		order.setTotal_amount(totalAmntInCart);
		//order.setOrderid(order.getOrderid() + 1);
		order.setCust_email(userDetailsBean.getEmail());
		order.setDate(new java.sql.Date(new Date().getTime()));
		try {
			for (CartInfoBean cartInfoBean : cartInfoBeans) {
				orders_Details = new Orders_Details();
				//System.out.println("orderid:"+order.getOrderid() +" eamil: "+ order.getCust_email() +" total:"+order.getTotal_amount());
				orders_Details.setOrderId(order);
				orders_Details.setProduct_code(cartInfoBean.getCode());
				orders_Details.setProduct_price(cartInfoBean.getPrice());
				orders_Details.setQuantity(cartInfoBean.getQuantity());
				int productTotal = (((Double)cartInfoBean.getPrice()).intValue() * cartInfoBean.getQuantity());
				orders_Details.setTotal_amount(productTotal);
				session.save(orders_Details);
			}

			session.close();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getTotalAmountInCartItems(Set<CartInfoBean> cartInfoBeans){
		int totalAmount = 0;
		
		Iterator<CartInfoBean> itr = cartInfoBeans.iterator();
		while (itr.hasNext()) {
			CartInfoBean cartInfoBean = (CartInfoBean) itr.next();
			Double dd = (Double) cartInfoBean.getPrice();
			int qty = cartInfoBean.getQuantity();
			totalAmount += (dd.intValue()* qty);
		}
		return totalAmount;
	}
	public Orders getMaxId(UserDetailsBean userDetailsBean) {
		Orders o = new Orders();
		System.out.println(" in getmaxid ------");
		Session session = hibernateDaoImpl.getSessionFactory().openSession();
		DetachedCriteria maxOrderId = DetachedCriteria.forClass(Orders.class)
				.setProjection(Projections.max("orderid"));
		@SuppressWarnings("deprecation")
		Orders order = (Orders) session
				.createCriteria(Orders.class)
				.add(org.hibernate.criterion.Property.forName("orderid").eq(
						maxOrderId)).uniqueResult();
		System.out.println(" in save order ------"+ order);
		if(order == null){
			order = new Orders();
			order.setOrderid(0);
		}
		session.clear();
		session.close();
		return o;
	}
}
