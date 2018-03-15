package com.shoppingcart.DAO.impl;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.shoppingcart.Beans.ProductsBean;
import com.shoppingcart.DAO.ProductsDao;
import com.shoppingcart.Model.Products;

@Repository
public class ProductsDaoImpl implements ProductsDao {

	@Autowired
	private HibernateDaoImpl hibernateDaoImpl;

	public void saveProducts(ProductsBean productsBean) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		try {
			session = hibernateDaoImpl.getSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();
			Products products = new Products();
			String path = "D:/Santosh_Workspace/Kepler_Workspace/ShoppingCart/src/main/webapp/resources/images";
			for (MultipartFile file : productsBean.getData()) {

				if (file.isEmpty()) {
					continue; // next pls
				}
				try {
					byte[] bytes = file.getBytes();
					BufferedOutputStream bout = new BufferedOutputStream(
							new FileOutputStream(path + "/"
									+ file.getOriginalFilename()));
					bout.write(bytes);
					bout.flush();
					bout.close();
					products.setCode(productsBean.getCode());
					products.setName(productsBean.getName());
					products.setCreate_date(new Date());
					products.setPrice(productsBean.getPrice());
					products.setFilename(file.getOriginalFilename());

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			session.save(products);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public ProductsBean getProductsDetail(String productCode) {
		// TODO Auto-generated method stub
		Session session = null;
		ProductsBean productsBean = new ProductsBean();
		try {
			session = hibernateDaoImpl.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Products> query = builder.createQuery(Products.class);
			Root<Products> root = query.from(Products.class);
			query.select(root).where(
					builder.equal(root.get("code"), productCode));
			Query<Products> q = session.createQuery(query);
			Products products = q.getSingleResult();

			productsBean.setCode(products.getCode());
			productsBean.setName(products.getName());
			productsBean.setPrice(products.getPrice());
			productsBean.setFilename(products.getFilename());
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return productsBean;
	}

	/**
	 * 
	 */
	public List<ProductsBean> getProductList() {
		// TODO Auto-generated method stub
		Session session = null;
		List<Products> proList = null;
		List<ProductsBean> productList = new ArrayList<ProductsBean>();
		ProductsBean productsBean = null;
		try {

			session = hibernateDaoImpl.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Products> query = builder.createQuery(Products.class);
			Root<Products> root = query.from(Products.class);
			query.select(root);
			Query<Products> q = session.createQuery(query);
			proList = q.getResultList();
			for (Products pro : proList) {
				productsBean = new ProductsBean();
				productsBean.setCode(pro.getCode());
				productsBean.setName(pro.getName());
				productsBean.setPrice(pro.getPrice());
				productsBean.setCreate_date(pro.getCreate_date());
				productsBean.setFilename(pro.getFilename());
				productList.add(productsBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return productList;
	}

	public ProductsBean getProductToCart(String productCode) {
		// TODO Auto-generated method stub
		Session session = null;
		ProductsBean productsBean = null;

		try {

		} catch (Exception e) {
			e.printStackTrace();
		}

		return productsBean;
	}

	/**
	 * Method used to edit product by manager or employee or new user.
	 */
	public void editProducts(ProductsBean productsBean) {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction transaction = null;
		try {
			session = hibernateDaoImpl.getSessionFactory().openSession();
			Products products = session.byId(Products.class).load(
					productsBean.getCode());
			transaction = session.beginTransaction();
			String path = "D:/Santosh_Workspace/Kepler_Workspace/ShoppingCart/src/main/webapp/resources/images";
			if (productsBean.getData() != null) {
				for (MultipartFile file : productsBean.getData()) {

					if (file.isEmpty()) {
						continue; // next pls
					}
					try {
						byte[] bytes = file.getBytes();
						BufferedOutputStream bout = new BufferedOutputStream(
								new FileOutputStream(path + "/"
										+ file.getOriginalFilename()));
						bout.write(bytes);
						bout.flush();
						bout.close();
						products.setName(productsBean.getName());
						products.setCreate_date(new Date());
						products.setPrice(productsBean.getPrice());
						if (file != null && file.getOriginalFilename() != null) {
							products.setFilename(file.getOriginalFilename());
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else {
				products.setName(productsBean.getName());
				products.setCreate_date(new Date());
				products.setPrice(productsBean.getPrice());

			}
			session.flush();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/**
	 * Delete product from database.
	 */
	public void deleteProduct(ProductsBean productsBean) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		try {
			session = hibernateDaoImpl.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Products products = session.byId(Products.class).load(
					productsBean.getCode());
			session.delete(products);
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public double getPrice(String productCode) {
		// TODO Auto-generated method stub
		Session session = hibernateDaoImpl.getSessionFactory().openSession();
		Products product = session.get(Products.class, productCode);
		double price = product.getPrice();
		session.close();
		return price;
	}
	
	/**
	 * Method used to delete the product from database.
	 */
	public void deleteProductByCode(String productCode) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		try {
			session = hibernateDaoImpl.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Products products = session.byId(Products.class).load(
					productCode);
			session.delete(products);
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}
