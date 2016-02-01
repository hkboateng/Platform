package com.boateng.abankus.service.impl;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

import org.hibernate.CacheMode;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.boateng.abankus.application.interfaces.CustomerService;
import com.boateng.abankus.domain.Address;
import com.boateng.abankus.domain.Authenticatecustomer;
import com.boateng.abankus.domain.ContactPerson;
import com.boateng.abankus.domain.Customer;
import com.boateng.abankus.domain.CustomerAccount;
import com.boateng.abankus.domain.Email;
import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.Phone;
import com.boateng.abankus.employees.utils.EmployeeUtils;
import com.boateng.abankus.utils.SecurityUtils;

@Component
public class CustomerServiceImpl implements CustomerService {

	private static final Logger logger = Logger.getLogger(CustomerServiceImpl.class.getName());
	
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
					
					customers = saveCustomerContact(session,email,phone,address,customers);
					session.save(customers);
					Authenticatecustomer auth = addCustomerAuthentication(customers);
					session.save(auth);
					session.flush();
					return customers;
		}catch(Exception e){
			logger.warning(e.getMessage());
			return null;
		}
	}
	
	private Customer saveCustomerContact(Session session,Email email, Phone phone, Address address,Customer customers){
		if(session.isOpen()){
			session.save(email);
			session.save(phone);
			session.save(address);
			customers.setAddressId(address);
			customers.setPhoneId(phone);
			customers.setEmailId(email);
		}
		return customers;
	}
	/**
	 * @param customers
	 * @throws Exception 
	 */
	private Authenticatecustomer addCustomerAuthentication(Customer customers) throws Exception {
		String pin = SecurityUtils.generateCustomerPIN();
		Authenticatecustomer auth = new Authenticatecustomer();
		auth.setCustomer(customers);
		String encryptedPIN = SecurityUtils.generateStorngPasswordHash(pin);
		auth.setPin(encryptedPIN);	
		auth.setPasscode(pin);
		return auth;
		
	}

	@Transactional	
	@Override
	public Customer findCustomerByCustomerNumber(String customerNo){
		Session session = getSessionFactory().getCurrentSession();
		Customer list = (Customer) session.createQuery("from Customer where customerNumber =?")
				.setParameter(0, customerNo)
				.uniqueResult();

		return list;
	}

	
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

		 Customer customer = (Customer) session.createQuery("From Customer where customerId=:customerId")
				 						.setParameter("customerId", id)
				 						.uniqueResult();
		return customer;
	}

	/* (non-Javadoc)
	 * @see com.boateng.abankus.employee.interfaces.CustomerService#findCustomerAccountByCustomerNumber(java.lang.String)
	 */
	
	@Transactional
	@Override
	public CustomerAccount findCustomerAccountByAccountNumber(String accountNumber) {
		
		Session session = getSessionFactory().getCurrentSession();
		CustomerAccount customerAccount = (CustomerAccount) session.createQuery("From CustomerAccount where accountNumber =:accountNumber")
						.setParameter("accountNumber", accountNumber)
						.uniqueResult();
						
		return customerAccount;
	}
	
	@Transactional
	@Override
	public List<Address> findCustomerAddressByCustomerId(int Id){
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
	public CustomerAccount findCustomerAccountByCustomerId(int customerId) {
		Session session = getSessionFactory().getCurrentSession();
		String sqlQuery = "From CustomerAccount where customerId=:customerId";
		CustomerAccount account = (CustomerAccount)session.createQuery(sqlQuery)
								.setParameter("customerId", customerId)
								.uniqueResult();
		return account;
	}

	/* (non-Javadoc)
	 * @see com.boateng.abankus.customer.service.CustomerService#findCustomerByEmailAddress(java.lang.String)
	 */
	@Override
	@Transactional
	public Email findCustomerByEmailAddress(String emailAddress) {
		Session session = getSessionFactory().getCurrentSession();
		String query = "From Email e where e.emailAddress=:email";
		Email email = (Email) session.createQuery(query)
						.setParameter("email", emailAddress)
						.uniqueResult();

			return email;
	}

	/* (non-Javadoc)
	 * @see com.boateng.abankus.customer.service.CustomerService#findCustomerEmailByCustomerId(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Email> findCustomerEmailByCustomerId(int customerId) {
		Session session = getSessionFactory().getCurrentSession();
		List<Email> email =	session.createQuery("from Email e where e.customer.customerId=:customerId")
							.setParameter("customerId", customerId)
							.list();
		return email;
	}

	/* (non-Javadoc)
	 * @see com.boateng.abankus.customer.service.CustomerService#findCustomerPhoneByCustomerId(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Phone> findCustomerPhoneByCustomerId(int customerId) {
		Session session = getSessionFactory().getCurrentSession();
		List<Phone> phone = session.createQuery("from Phone p where p.customer.customerId=:customerId")
							.setParameter("customerId", customerId)
							.list();
		return phone;
	}

	@Transactional
	@Override	
	public CustomerAccount addEmployeeSalesAccount(Employee employee,Customer customer,String industry,String notes){
		CustomerAccount customerAccount = new CustomerAccount();
		Session session = getSessionFactory().getCurrentSession();
		String accountNo  = EmployeeUtils.generateAccountNumber();
		customerAccount.setAccountNumber(accountNo);
		customerAccount.setCustomer(customer);
		customerAccount.setStatus("Active");
		customerAccount.setIndustry(industry);
		customerAccount.setNotes(notes);
		customerAccount.setEmployee(employee);
		session.save(customerAccount);
		session.flush();
		return customerAccount;
	}
	
	@Transactional
	@Override
	public void saveCustomerPin(String pin,Customer customer,Session session) throws Exception{
		session = getSessionFactory().getCurrentSession();
		Authenticatecustomer auth = new Authenticatecustomer();
		auth.setCustomer(customer);
		String encryptedPIN = SecurityUtils.encode(pin);
		auth.setPin(encryptedPIN);
		auth.setPasscode(pin);
		session.save(auth);
	}

	/* (non-Javadoc)
	 * @see com.boateng.abankus.customer.service.CustomerService#findCustomerByCustomerIdAndPinCode(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public Customer findCustomerByCustomerIdAndPinCode(int customerId,String pinCode) {
		Session session = getSessionFactory().getCurrentSession();
		String query = "From Authenticatecustomer ac where ac.customer.customerId = :customerId and ac.passcode=:pinCode";
		Customer customer = (Customer) session.createQuery(query)
				.setLockMode("customerpasscode", LockMode.PESSIMISTIC_WRITE)
							.setParameter("customerId", customerId)
							.setParameter("pinCode", pinCode)
							.uniqueResult();
		return customer;
	}

	/* (non-Javadoc)
	 * @see com.boateng.abankus.customer.service.CustomerService#findCustomerById(int)
	 */
	@Override
	@Transactional
	public Authenticatecustomer findCustomerById(int id) {
		Session session = getSessionFactory().getCurrentSession();
		String query = "From Authenticatecustomer ac where ac.customer.customerId = :customerId";
		Authenticatecustomer customer = (Authenticatecustomer) session.createQuery(query)
				.setLockMode("customerpasscode", LockMode.PESSIMISTIC_WRITE)
							.setParameter("customerId", id)
							.uniqueResult();
		return customer;
	}

	/* (non-Javadoc)
	 * @see com.boateng.abankus.customer.service.CustomerService#findCustomerByEmail(java.lang.String)
	 */
	@Override
	@Transactional
	public Customer findCustomerByEmail(String email) {
		Session session = getSessionFactory().getCurrentSession();
		Customer customer = (Customer)session.createQuery("From Customer c where c.emailId.emailAddress =:email")
				.setParameter("email", email)
							.uniqueResult();
		
		return customer;
	}

	/* (non-Javadoc)
	 * @see com.boateng.abankus.customer.service.CustomerService#findCustomerByFirstNameAndLastName(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public Customer findCustomerByFirstNameAndLastName(String firstname,String lastname) {
		Session session = getSessionFactory().getCurrentSession();
		Customer customer = (Customer) session.createQuery("From Customer c where c.firstname =:firstname and c.lastname =:lastname")
							.setParameter("firstname", firstname)
							.setParameter("lastname", lastname)
							.uniqueResult();
		return customer;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Customer> findCustomerByFirstName(String firstname){
		Session session = getSessionFactory().getCurrentSession();
		List<Customer> customer = session.createQuery("From Customer c where c.firstname =:firstname")
							.setParameter("firstname", firstname)
							.list();
		return customer;		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Customer> findCustomerByLastName(String lastname){
		Session session = getSessionFactory().getCurrentSession();
		List<Customer> customer = session.createQuery("From Customer c where c.lastname =:lastname")
							.setParameter("lastname", lastname)
							.list();
		return customer;		
	}

	/* (non-Javadoc)
	 * @see com.boateng.abankus.application.interfaces.CustomerService#updateCustomerContactPerson(com.boateng.abankus.domain.ContactPerson)
	 */
	@Override
	@Transactional
	public void updateCustomerContactPerson(ContactPerson person,int customerId) {
		Session session = getSessionFactory().getCurrentSession();
		Customer customer = (Customer) session.get(Customer.class, customerId,LockOptions.READ);
		session.save(person);
		
		session.update(customer);
	}

	@Override
	@Transactional
	public ContactPerson findCustomerContactByCustomerId(int customerId) {
		Session session = getSessionFactory().getCurrentSession();
		ContactPerson contactPerson = (ContactPerson) session.createQuery("from ContactPerson cp where cp.customer.customerId =:customerId")
				.setParameter("customerId", customerId)
				.uniqueResult();
		return contactPerson;
	}

	private Lock lock = new ReentrantLock();
	/* (non-Javadoc)
	 * @see com.boateng.abankus.application.interfaces.CustomerService#findCustomerByAccountNumber(java.lang.String)
	 */
	@Override
	@Transactional
	public Customer findCustomerByAccountNumber(String accountNumber) {
		Session session = getSessionFactory().getCurrentSession();
		Customer customer = null;
		CustomerAccount customerAccount =	(CustomerAccount) session.createQuery("from CustomerAccount ca where ca.accountNumber =:accountNumber")
				.setParameter("accountNumber", accountNumber).uniqueResult();
		if(customerAccount != null){
			customer = (Customer) session.get(Customer.class, customerAccount.getCustomer().getCustomerId());
		}
		return customer;
	}

	/* (non-Javadoc)
	 * @see com.boateng.abankus.application.interfaces.CustomerService#countTotalCustomer()
	 */
	@Override
	@Transactional
	public Integer countTotalCustomer() {
		Integer total = 0;
		Session session = getSessionFactory().getCurrentSession();
		
		total = session.createQuery("from Customer").list().size();
		
		return total;
	}

	/* (non-Javadoc)
	 * @see com.boateng.abankus.application.interfaces.CustomerService#findCustomerLikeFirstNameAndLastName(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Customer> findCustomerLikeFirstNameAndLastName(String firstname, String lastname) {
		Session session = getSessionFactory().getCurrentSession();
		/**
		List<Customer> customers = session.createSQLQuery("")
									.setParameter("firstname", firstname)
									.setParameter("lastname", lastname)
									.setCacheable(true)
									.setCacheMode(CacheMode.NORMAL)
									.list();
									**/
		List<Customer> customers =session.createCriteria(Customer.class)
									.add(Restrictions.ilike("firstname", firstname, MatchMode.START))
									.add(Restrictions.ilike("lastname", lastname, MatchMode.START))
									.list();
		return customers;
	}
}
