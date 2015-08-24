package com.boateng.abankus.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Cacheable;

import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.boateng.abankus.customer.service.CustomerService;
import com.boateng.abankus.domain.Address;
import com.boateng.abankus.domain.Customer;
import com.boateng.abankus.domain.CustomerAccount;
import com.boateng.abankus.domain.Email;
import com.boateng.abankus.domain.Phone;

@Component

public class CustomerServiceImpl implements CustomerService {

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
	/**
	 * 
	 * @param customers
	 * @return 
	 */
	@Transactional
	@Override
	
	public Customer addNewCustomer(Customer customers,Email email, Phone phone,Address address){
		try{
			Session session = getSessionFactory().getCurrentSession();
					Serializable i = session.save(customers);
					email.setCustomer(customers);
					phone.setCustomer(customers);
					address.setCustomer(customers);
					session.save(email);
					session.save(address);
					session.save(phone);
					session.flush();
					return customers;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional	
	@SuppressWarnings("unchecked")
	@Override
	public Customer findCustomerByCustomerNumber(String customerNo){
		Session session = getSessionFactory().getCurrentSession();
		List<Customer> list = session.createQuery("from Customer where customerNumber =?")
				.setParameter(0, customerNo)
				.setCacheable(true)
				.list();
		
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.boateng.abankus.employee.interfaces.CustomerService#getAllCustomers()
	 */
	
	@Transactional	
	@Override
	public List<Customer> getAllCustomers() {
		 Session session = getSessionFactory().getCurrentSession();
		 
		@SuppressWarnings("unchecked")
		List<Customer> query = session.createQuery("from Customer")
				.setCacheable(true)
				.list();
		
		return query;

	}

	/* (non-Javadoc)
	 * @see com.boateng.abankus.employee.interfaces.CustomerService#findCustomerByCustomerId(long)
	 */
	@Override
	@Transactional	
	public Customer findCustomerByCustomerId(int id) {
		 Session session = getSessionFactory().getCurrentSession();

		 Customer customer = (Customer) session.createQuery("From Customer where customerId=?").setParameter(0, id).uniqueResult();
		return customer;
	}

	/* (non-Javadoc)
	 * @see com.boateng.abankus.employee.interfaces.CustomerService#findCustomerAccountByCustomerNumber(java.lang.String)
	 */
	
	@Transactional
	@Override
	public CustomerAccount findCustomerAccountByAccountNumber(String accountNumber) {
		
		Session session = getSessionFactory().getCurrentSession();
		CustomerAccount customerAccount = (CustomerAccount) session.createQuery("From CustomerAccount where accountNumber =?")
						.setParameter(0, accountNumber)
						.setCacheMode(CacheMode.NORMAL)
						.uniqueResult();
						
		return customerAccount;
	}
	
	@Transactional
	@Override
	public List<Address> findAddressByCustomerId(int Id){
		Session session = getSessionFactory().getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Address> address =  session.createQuery("From Address where customerId=?")
										.setParameter(0, Id)
										.list();
		
		return address;
	}

	/* (non-Javadoc)
	 * @see com.boateng.abankus.customer.service.CustomerService#findCustomerAccountByCustomerNumber(java.lang.String)
	 */
	@Override
	@Transactional
	public CustomerAccount findCustomerAccountByCustomerNumber(String customerNumber) {
		Session session = getSessionFactory().getCurrentSession();
		String sqlQuery = "From CustomerAccount where customerNumber =?";
		Object account = session.createQuery(sqlQuery)
								.setParameter(0, customerNumber)
								.uniqueResult();
		return (CustomerAccount) account;
	}
}
