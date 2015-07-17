/**
 * hkboateng
 */
package com.boateng.abankus.service.impl;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.boateng.abankus.domain.Product;
import com.boateng.abankus.employee.interfaces.ProductService;


/**
 * @author hkboateng
 *
 */
public class ProductServiceImpl implements ProductService{

	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * 
	 * @param sessionFactory
	 */

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	@Override
	public Product saveProduct(Product product){
		
		Session session = sessionFactory.getCurrentSession();
		session.save("product", product);
		session.flush();
		return null;
	}
	
	public Product updateProduct(int productId){
		Session session = sessionFactory.getCurrentSession();
		Product product = (Product) session.load(Product.class, productId);
		session.update(product);
		
		return product;
	}
	
	public boolean isProductNumberUnique(String productNumber){
		Session session = sessionFactory.getCurrentSession();
		List query = session.createQuery("From Product where productNumber =?")
				.setParameter(0, productNumber)
				.list();
		if(query.size() > 0){
			return true;
		}
		return false;
	}
}
