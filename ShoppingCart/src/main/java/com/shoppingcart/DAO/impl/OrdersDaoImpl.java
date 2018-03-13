package com.shoppingcart.DAO.impl;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.shoppingcart.DAO.OrdersDao;
import com.shoppingcart.Model.Orders;

public class OrdersDaoImpl implements OrdersDao {

	@Autowired
	private HibernateDaoImpl hibernateDaoImpl;
	
	public void setOrderInCart(String productCode) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
		Session session = null;
		try {
			session = hibernateDaoImpl.getSessionFactory().openSession();
			transaction = session.getTransaction();
			CriteriaBuilder ctrBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Orders> query = ctrBuilder.createQuery(Orders.class);
			Root<Orders> root = query.from(Orders.class);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	public void saveOrder() {
		// TODO Auto-generated method stub
		int orderid = getOrderId();
		Orders order = new Orders();
		order.setOrderid(orderid);
		order.setTotal_amount(1000);
		order.setCust_email("santosh@gmail.com");
		order.setDate(new Date());
		Session session =  hibernateDaoImpl.getSessionFactory().openSession();
		session.save(order);
		session.close();
	}
	public int getOrderId() {
		
		return 100;
	}

}
