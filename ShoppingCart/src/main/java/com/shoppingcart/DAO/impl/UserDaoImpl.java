package com.shoppingcart.DAO.impl;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import com.shoppingcart.Beans.UserDetailsBean;
import com.shoppingcart.DAO.UserDao;
import com.shoppingcart.Model.LoginBean;
import com.shoppingcart.Model.UserDetails;

public class UserDaoImpl implements UserDao {

	@Autowired
	private HibernateDaoImpl hibernateDaoImpl;
	
	public void insertNewUser() {
		// TODO Auto-generated method stub
		
	}

	public UserDetails findByUserId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserDetailsBean getPassword(LoginBean bean) {
		// TODO Auto-generated method stub
		Session session = hibernateDaoImpl.getSessionFactory().openSession();
		String query = " from UserDetails where username = '"+ bean.getUsername()+"'";
		UserDetails userdetails = (UserDetails) session.createQuery(query).uniqueResult();
		if(userdetails != null){
			UserDetailsBean userbean = new UserDetailsBean();
			userbean.setName(userdetails.getName());
			userbean.setPassword(userdetails.getPassword());
			userbean.setEmail(userdetails.getEmail());
			userbean.setUserrole(userdetails.getUserrole());
			userbean.setPhone(userdetails.getPhone());
			userbean.setAddress(userdetails.getAddress());
			return userbean;
		}
		return null;
		
	}

}
