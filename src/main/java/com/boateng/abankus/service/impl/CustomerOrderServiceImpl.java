/**
 * hkboateng
 */
package com.boateng.abankus.service.impl;


import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.boateng.abankus.domain.Customer;
import com.boateng.abankus.domain.CustomerOrder;
import com.boateng.abankus.services.CustomerOrderService;

/**
 * @author hkboateng
 *
 */
@Component
public class CustomerOrderServiceImpl implements CustomerOrderService{
	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Transactional
	@Override
	public CustomerOrder saveCustomerOrder(CustomerOrder order){
		Session session = sessionFactory.getCurrentSession();
		session.save("clientorder", order);
		return order;
	}
	/* (non-Javadoc)
	 * @see com.boateng.abankus.services.CustomerOrderService#findAllCustomerOrderByCustomerId(com.boateng.abankus.domain.Customer)
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<CustomerOrder> findAllCustomerOrderByCustomerId(int customer) {
		Session session = getSessionFactory().getCurrentSession();
		 List<CustomerOrder> customerOrderList = session.createQuery("from CustomerOrder c where c.customer.customerId = :customerId")
												.setParameter("customerId", customer)
												.list();
		 
		return  customerOrderList;
	}

	

}
