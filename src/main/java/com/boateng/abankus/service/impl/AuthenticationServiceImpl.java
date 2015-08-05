package com.boateng.abankus.service.impl;



import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.Role;
import com.boateng.abankus.domain.User;
import com.boateng.abankus.services.AuthenticationService;
import com.boateng.abankus.utils.SecurityUtils;

@Component
public class AuthenticationServiceImpl  implements AuthenticationService{
	private final Logger log = Logger.getLogger( AuthenticationServiceImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	@Transactional
	@SuppressWarnings("unchecked")
	
	public User AuthenticateUser(String username,String password) {
		@SuppressWarnings("unused")
		UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(username,password);
		
		List<User> login = new ArrayList<User>();
		login = sessionFactory.getCurrentSession()
				.createQuery("from User where username=?")
				.setCacheable(true)
				.setParameter(0, username)
				.list();
		/**** Validating User's Password****/
		if(login.size() == 1){
			User user = login.get(0);
			boolean validate = authenticateUser(password,user.getPassword());
			if(validate){
				return user;
			}
		}
		return null;

	}

	@Transactional
	@SuppressWarnings("unchecked")

	public List<Role> findRoleByUser(String username) {
		List<Role> roles = getSessionFactory().getCurrentSession()	
				 .createQuery("role from UserRole where username=?")
				 .setParameter(0, username)
				 .setCacheable(true)
				 .setCacheMode(CacheMode.GET)
				 .list();
		return roles;
	}

	@Transactional
	@Override
	@SuppressWarnings("unchecked")
	public  List<Role> getAllRoles(){
		List<Role> roles = getSessionFactory().getCurrentSession()	
						 .createCriteria(Role.class)
						 .list();
		return roles;
	}
	private boolean authenticateUser(String plain,String passwd){
		return SecurityUtils.authenticatePassword(plain, passwd);
	}

	@SuppressWarnings("rawtypes")
	@Transactional	
	@Override
	public User findUserByUserName(String username) {
		List query = getSessionFactory().getCurrentSession()
				.createQuery("from User where username=?")
				.setParameter(0, username)
				.list();
		return (User) query.get(0);
	}

	@SuppressWarnings("rawtypes")
	@Transactional
	@Override
	public boolean doEmailExist(String email){
		List query = getSessionFactory().getCurrentSession()
					.createQuery("from Employee where email=?")
					.setParameter(0, email)
					.list();
		if(query.size() < 1 ){
			return false;
		}
		return true;
	}
	
	@SuppressWarnings("rawtypes")
	@Transactional
	@Override
	public boolean doUserNameExist(String username) {
		List query = getSessionFactory().getCurrentSession()
				.createQuery("from User where username=?")
				.setParameter(0, username)
				.list();
			if(query.size() < 1 ){
				return false;
			}
			return true;
	}

	@Transactional	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean isEmployeeIdExist(String employeeId){
		List query = getSessionFactory().getCurrentSession()
				.createQuery("from Employee where employeeid=?")
				.setParameter(0, employeeId)
				.list();	
		if(query.size() > 0){
			return true;
		}else{
			return false;
		}
	}
	
	@Transactional	
	@Override
	public Employee findEmployeeByUserName(String username) {
		Query userQuery = getSessionFactory().getCurrentSession()
							.createQuery("from User where username=?")
							.setParameter(0, username)
							.setCacheable(true);
		User user = (User) userQuery.uniqueResult();
		
		/** Finding Employee using username from User **/
		if(user == null){
			return null;
		}
		List employee = getSessionFactory().getCurrentSession()
								.createQuery("from Employee where employeeId=?")
								.setParameter(0, user.getEmployeeId())
								.list();
		if(employee == null || employee.size() < 1){
			return null;
		}
		Employee emp = (Employee) employee.get(0);
		return emp;
	}
	

	
}
