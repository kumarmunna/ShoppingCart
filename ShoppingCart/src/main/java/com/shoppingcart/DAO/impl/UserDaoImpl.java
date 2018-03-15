package com.shoppingcart.DAO.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.shoppingcart.Beans.UserDetailsBean;
import com.shoppingcart.DAO.UserDao;
import com.shoppingcart.Model.LoginBean;
import com.shoppingcart.Model.Products;
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
		List<UserDetails> userdetails = (List<UserDetails>) session.createQuery(query).list();
		if(userdetails != null){
			UserDetailsBean userbean = new UserDetailsBean();
			for(UserDetails userDetails2 : userdetails){
				
				if(bean.getPassword().equals(userDetails2.getPassword())){
					userbean.setUsername(userDetails2.getUsername());
					userbean.setName(userDetails2.getName());
					userbean.setPassword(userDetails2.getPassword());
					userbean.setEmail(userDetails2.getEmail());
					userbean.setUserrole(userDetails2.getUserrole());
					userbean.setPhone(userDetails2.getPhone());
					userbean.setAddress(userDetails2.getAddress());
					break;
				}
				
				
			}
			return userbean;
		}
		return null;
		
	}
/**
 * 
 */
	public UserDetailsBean updateUserAddress(UserDetailsBean userbean) {
		// TODO Auto-generated method stub
		UserDetailsBean userDetails = null;
		
		try{
			Session session = hibernateDaoImpl.getSessionFactory().openSession();
//			UserDetails details = session.byId(UserDetails.class).load("email");
			
			Transaction tr = session.beginTransaction();
			
			String hqlUpdate = "update UserDetails c set c.address = :address where c.email = :email";
			// or String hqlUpdate = "update Customer set name = :newName where name = :oldName";
			@SuppressWarnings("deprecation")
			int updatedEntities = session.createQuery( hqlUpdate )
			        .setString( "address", userbean.getAddress())
			        .setString( "email", userbean.getEmail().toString() )
			        .executeUpdate();
			tr.commit();
			session.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return userDetails;
	}

public UserDetailsBean getUserByEmailId(String email) {
	// TODO Auto-generated method stub
	Session session = hibernateDaoImpl.getSessionFactory().openSession();
	CriteriaBuilder builder = session.getCriteriaBuilder();
	CriteriaQuery<UserDetails> query = builder.createQuery(UserDetails.class);
	Root<UserDetails> root = query.from(UserDetails.class);
	query.select(root).where(
			builder.equal(root.get("email"), email));
	@SuppressWarnings("deprecation")
	Query<UserDetails> q = session.createQuery(query);
	UserDetails details = q.list().get(0);
	UserDetailsBean bean = new UserDetailsBean();
	bean.setName(details.getName());
	bean.setAddress(details.getAddress());
	bean.setEmail(details.getEmail());
	return bean;
}
}
