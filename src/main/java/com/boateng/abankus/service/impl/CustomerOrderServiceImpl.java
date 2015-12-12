/**
 * hkboateng
 */
package com.boateng.abankus.service.impl;


import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
	Logger logger = Logger.getLogger(CustomerOrderServiceImpl.class.getName());
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
	public CustomerOrder saveCustomerOrder(CustomerOrder order,Integer customerId){
		Session session = sessionFactory.getCurrentSession();
		Customer customer = (Customer) session.get(Customer.class,customerId);
		order.setCustomer(customer);
		session.save("clientorder", order);
		customer = null;
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

	@Transactional
	@Override
	public CustomerOrder findCustomerOrderByOrderNumber(String orderNumber) {

		Session session = getSessionFactory().getCurrentSession();
		String query = "From CustomerOrder c where c.orderNumber = :orderNumber";
		CustomerOrder customerOrder = (CustomerOrder) session.createQuery(query)
										.setParameter("orderNumber", orderNumber)
										.uniqueResult();
		return customerOrder;
	}

	

}
