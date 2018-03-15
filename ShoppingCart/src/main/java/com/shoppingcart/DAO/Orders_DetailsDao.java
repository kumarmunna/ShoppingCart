package com.shoppingcart.DAO;

import java.util.List;
import java.util.Set;

import com.shoppingcart.Beans.CartInfoBean;
import com.shoppingcart.Beans.OrderDetailsBean;
import com.shoppingcart.Beans.OrderListBean;
import com.shoppingcart.Beans.UserDetailsBean;

public interface Orders_DetailsDao {

	public void saveOrder(Set<CartInfoBean> cartInfoBeans,UserDetailsBean userdetails);
	public List<OrderListBean> orderList();
	public List<OrderDetailsBean> getOrderByOrderId(int orderid);
}
