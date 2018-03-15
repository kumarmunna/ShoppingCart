package com.shoppingcart.DAO.impl;

import java.awt.image.BandCombineOp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.shoppingcart.Beans.CartInfoBean;
import com.shoppingcart.Beans.OrderDetailsBean;
import com.shoppingcart.Beans.OrderListBean;
import com.shoppingcart.Beans.ProductsBean;
import com.shoppingcart.Beans.UserDetailsBean;
import com.shoppingcart.DAO.Orders_DetailsDao;
import com.shoppingcart.DAO.ProductsDao;
import com.shoppingcart.DAO.UserDao;
import com.shoppingcart.Model.Orders;
import com.shoppingcart.Model.Orders_Details;

public class Orders_DetailsDaoImpl implements Orders_DetailsDao {

	@Autowired
	HibernateDaoImpl hibernateDaoImpl;
	@Autowired
	UserDao userDao;
	@Autowired
	ProductsDao productsDao;

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

	public List<OrderListBean> orderList() {
		// TODO Auto-generated method stub
		Session session = hibernateDaoImpl.getSessionFactory().openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Orders> qurey = builder.createQuery(Orders.class);
		Root<Orders> root = qurey.from(Orders.class);
		qurey.select(root);
		Query<Orders> qq = session.createQuery(qurey);
		List<Orders> lst = qq.list();
		OrderListBean orderbean = null;
		List<OrderListBean> list = new ArrayList<OrderListBean>();
		for (Iterator<Orders> iterator = lst.iterator(); iterator.hasNext();) {
			Orders orders = (Orders) iterator.next();
			orderbean = new OrderListBean();
			orderbean.setOrderid(orders.getOrderid());
			orderbean.setEmail(orders.getCust_email());
			orderbean.setDate(orders.getDate());
			orderbean.setTotalamount(orders.getTotal_amount());
			UserDetailsBean userdetail = userDao.getUserByEmailId(orders.getCust_email());
			orderbean.setName(userdetail.getName());
			orderbean.setAddress(userdetail.getAddress());
			list.add(orderbean);
		}
		
		return list;
	}

	public List<OrderDetailsBean> getOrderByOrderId(int orderid) {
		// TODO Auto-generated method stub
		Session session = hibernateDaoImpl.getSessionFactory().openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Orders_Details> query = builder.createQuery(Orders_Details.class);
		Root<Orders_Details> root = query.from(Orders_Details.class);
		Orders order = new Orders();
		order.setOrderid(orderid);
		
		List list = session.createQuery("from Orders_Details where OrderId="+orderid).list();
		List<OrderDetailsBean> orderDetailList = new ArrayList<OrderDetailsBean>();
		OrderDetailsBean bean = null;
		System.out.println(" befor for loop ----->> ");
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Orders_Details orders_Details = (Orders_Details) iterator.next();
			bean = new OrderDetailsBean();
			System.out.println(" inside for loop with orderid----->> "+ orders_Details.getOrderId().getOrderid());
			bean.setOrderid(orders_Details.getOrderId().getOrderid());
			bean.setProductCode(orders_Details.getProduct_code());
			ProductsBean productsBean = productsDao.getProductsDetail(orders_Details.getProduct_code());
			bean.setProductName(productsBean.getName());
			bean.setProductPrice(orders_Details.getProduct_price());
			bean.setQuantity(orders_Details.getQuantity());
			bean.setTotalAmount(orders_Details.getTotal_amount());
			orderDetailList.add(bean);
		}
		return orderDetailList;
	}
}
