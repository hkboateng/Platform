/**
 * hkboateng
 */
package com.boateng.abankus.service.impl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
	
}
