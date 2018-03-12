package com.shoppingcart.DAO.impl;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import com.shoppingcart.DAO.UserRegistrationDao;
import com.shoppingcart.Model.UserDetails;

public class UserRegistrationDaoImpl implements UserRegistrationDao {

	@Autowired
	HibernateDaoImpl hibernateDaoImpl;
	
	public void saveUser(UserDetails userDetails) {
		// TODO Auto-generated method stub
		Session session = hibernateDaoImpl.getSessionFactory().openSession();
		session.save(userDetails);
		session.clear();
		session.close();
	}

}
