package com.shoppingcart.DAO;

import com.shoppingcart.Beans.UserDetailsBean;
import com.shoppingcart.Model.LoginBean;
import com.shoppingcart.Model.UserDetails;

public interface UserDao {

	public void insertNewUser();
	public UserDetails findByUserId(int id);
	public UserDetailsBean getPassword(LoginBean bean);
	
	
}
