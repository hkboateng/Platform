package com.boateng.abankus.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.boateng.abankus.domain.Customer;
import com.boateng.abankus.domain.CustomerAccount;
import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.User;
import com.boateng.abankus.domain.UserRole;
import com.boateng.abankus.employee.interfaces.AuthenticationService;
import com.boateng.abankus.employee.interfaces.EmployeeService;
import com.boateng.abankus.employees.utils.EmployeeUtils;

@Component
public class EmployeeServiceImpl implements EmployeeService{
	private final Logger log = Logger.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired(required=true)
	@Qualifier(value="authenticationServiceImpl")
	private AuthenticationService authenticationServiceImpl;
	
	
	
	 public void setSessionFactory(SessionFactory sessionFactory) {
	        this.sessionFactory = sessionFactory;
	    }
	 public SessionFactory getSessionFactory() {
		 return sessionFactory;
		 }



	@Transactional
	@Override
	public Employee saveEmployee(Employee e,User user, String[] role){
		try{
		if (e == null) {
			return null;
		}
		
		if (e.getEmployeeId() == null) {
			e.setEmployeeId(EmployeeUtils.generateEmployeeId());
		}
		Session session = getSessionFactory().getCurrentSession();

		session.persist(e);
		session.persist("User", user);
		
		/** Saving Employee UserRole **/

		for(int i=0;i< role.length;i++){
			UserRole userRole = new UserRole(user,role[i]);
			session.save(userRole);
			session.flush();	
			session.clear();
		}
		
		session.flush();
		
		return e;	
		}catch(HibernateException ex){
			ex.getMessage();
			return null;
		}
	}

	@Transactional
	@Override	
	public CustomerAccount addEmployeeSalesAccount(Employee employee,Customer customer){
		CustomerAccount customerAccount = new CustomerAccount();
		
		String accountNo  = EmployeeUtils.generateAccountNumber();
		customerAccount.setAccountNumber(accountNo);
		customerAccount.setCustomer(customer);
		customerAccount.setEmployee(employee);
		customerAccount.setStatus("Active");
		
		Session session = getSessionFactory().getCurrentSession();
		
		session.save(customerAccount);
		
		return customerAccount;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Employee> getAllEmployee(){
		Session session = getSessionFactory().getCurrentSession();
		List<Employee> listEmployee = 	session.createQuery("From Employee")
						.setCacheMode(CacheMode.NORMAL)
						.list();
		
		return listEmployee;
	}
	
	public Employee getEmployeeById(int employeeId){
		if(employeeId < 1){
			log.warn("Employee Id is Null.");
			return null;
		}
		Session session = getSessionFactory().getCurrentSession();
		Employee employee  = (Employee) session.load(Employee.class, employeeId);
		log.info("");
		return employee;
	}
}
