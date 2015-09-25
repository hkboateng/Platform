/**
 * hkboateng
 */
package com.boateng.abankus.service.impl;

import java.util.List;

import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.boateng.abankus.domain.Product;
import com.boateng.abankus.services.ProductService;


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
		return product;
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
	public List<Product> getAllProducts(){
		Session session = sessionFactory.getCurrentSession();
		
		List<Product> listProducts  = session.getNamedQuery("Product.findAll")
											.list();

		return listProducts;
	}


	@Override
	@Transactional
	public Product findProductByProductCode(String productCode) {
		Session session = sessionFactory.getCurrentSession();
		Product product = (Product) session.getNamedQuery("Product.findProductByCode")
							.setParameter(0, productCode)
							.uniqueResult();
		return product;
	}
	@Transactional	
	public Product updateProduct(String productCode, String productName, String description,float unitCost){
		Session session = sessionFactory.getCurrentSession();
		Product p = (Product) session.createQuery("from Product p where p.productCode=?")
					.setParameter("productCode", productCode)
					.uniqueResult();
		//session.
		if(productName != null || !productName.isEmpty()){
			p.setProductName(productName);
		}
		if(description != null || !description.isEmpty()){
			p.setDescription(description);
		}
		if(unitCost != 0.0f || unitCost != 0 || unitCost != 0.0){
			p.setUnitCost(unitCost);
		}
		session.update("product", p);
		return p;
	}
}
