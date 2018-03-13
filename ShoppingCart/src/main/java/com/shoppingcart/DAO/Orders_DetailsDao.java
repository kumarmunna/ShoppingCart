package com.shoppingcart.DAO;

import java.util.Set;

import com.shoppingcart.Beans.CartInfoBean;
import com.shoppingcart.Beans.UserDetailsBean;

public interface Orders_DetailsDao {

	public void saveOrder(Set<CartInfoBean> cartInfoBeans,UserDetailsBean userdetails);
	
}
