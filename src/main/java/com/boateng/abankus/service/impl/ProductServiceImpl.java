/**
 * hkboateng
 */
package com.boateng.abankus.service.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.CacheMode;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.query.spi.sql.NativeSQLQueryReturn;
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
	@Transactional
	@Override	
	public Product updateProduct(int productId){
		Session session = sessionFactory.getCurrentSession();
		Product product = (Product) session.load(Product.class, productId);
		session.update(product);
		
		return product;
	}
	@Transactional
	@Override	
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
	@Transactional
	@Override	
	@SuppressWarnings("unchecked")
	public Set<Product> getAllProducts(){
		Session session = sessionFactory.getCurrentSession();
		Set<Product> listProducts = new LinkedHashSet<Product>();	
		List<Product> list  = session.createQuery(" from Product")
											.setCacheMode(CacheMode.NORMAL)
											.list();
		for(Product l: list){
			listProducts.add(l);
		}
			
		return listProducts;
	}
}
