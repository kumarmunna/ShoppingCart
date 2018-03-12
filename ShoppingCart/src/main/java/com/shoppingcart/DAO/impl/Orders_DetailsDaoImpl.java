package com.shoppingcart.DAO.impl;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import com.shoppingcart.Beans.CartInfoBean;
import com.shoppingcart.DAO.Orders_DetailsDao;
import com.shoppingcart.Model.Orders;
import com.shoppingcart.Model.Orders_Details;

public class Orders_DetailsDaoImpl implements Orders_DetailsDao {

	@Autowired
	HibernateDaoImpl hibernateDaoImpl;

	public void saveOrder(Set<CartInfoBean> cartInfoBeans) {
		// TODO Auto-generated method stub
		Session session = hibernateDaoImpl.getSessionFactory().openSession();
		Orders_Details orders_Details = null;
		System.out.println("cartInfoBeans-------------->>>> "
				+ cartInfoBeans.toString());
		Orders id = getMaxId("santosh@gmail.com");
		try {
			for (CartInfoBean cartInfoBean : cartInfoBeans) {
				orders_Details = new Orders_Details();
				orders_Details.setOrderId(id);
				orders_Details.setProduct_code(cartInfoBean.getCode());
				orders_Details.setProduct_price(cartInfoBean.getPrice());
				orders_Details.setQuantity(cartInfoBean.getQuantity());
				orders_Details.setTotal_amount(cartInfoBean.getSubtotal());
				session.save(orders_Details);
			}
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Orders getMaxId(String emailid) {
		Orders o = new Orders();
		Session session = hibernateDaoImpl.getSessionFactory().openSession();
		DetachedCriteria maxOrderId = DetachedCriteria.forClass(Orders.class)
				.setProjection(Projections.max("orderid"));
		@SuppressWarnings("deprecation")
		Orders orderid = (Orders) session
				.createCriteria(Orders.class)
				.add(org.hibernate.criterion.Property.forName("orderid").eq(
						maxOrderId)).uniqueResult();
		o.setOrderid(orderid.getOrderid());
		o.setTotal_amount(100);
		o.setCust_email("santosh@gmail.com");
		session.clear();
		session.close();
		return o;
	}
}
