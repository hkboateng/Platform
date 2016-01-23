package com.boateng.abankus.service.impl;



import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.Permission;
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
	public User AuthenticateUser(String username) {
		User user = (User) sessionFactory.getCurrentSession()
				.createQuery("from User where username= :username")
				.setParameter("username", username)
				.uniqueResult();

		return user;

	}

	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	public List<Permission> findRoleByUser(String userId) {
		List<Permission> roles = getSessionFactory().getCurrentSession()	
				 .createQuery("role from UserPermission where userId= :userId")
				 .setParameter("username", userId)
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
				.createQuery("from User where username= :username")
				.setParameter("username", username)
				.list();
		return (User) query.get(0);
	}

	@SuppressWarnings("rawtypes")
	@Transactional
	@Override
	public boolean doEmailExist(String email){
		List query = getSessionFactory().getCurrentSession()
					.createQuery("from Employee where email= :email")
					.setParameter("email", email)
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
				.createQuery("from User where username= :username")
				.setParameter("username", username)
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
				.createQuery("from Employee where employeeid= :employeeId")
				.setParameter("employeeId", employeeId)
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
							.createQuery("from User where username= :username")
							.setParameter("username", username);
		User user = (User) userQuery.uniqueResult();
		
		/** Finding Employee using username from User **/
		if(user == null){
			return null;
		}
		List employee = getSessionFactory().getCurrentSession()
								.createQuery("from Employee where employeeId= :employeeId")
								.setParameter("employeeId", user.getEmployeeId())
								.list();
		if(employee == null || employee.size() < 1){
			return null;
		}
		Employee emp = (Employee) employee.get(0);
		return emp;
	}
	/* (non-Javadoc)
	 * @see com.boateng.abankus.services.AuthenticationService#AuthenticateUser(java.lang.String, java.lang.String)
	 */
	@Override
	public User AuthenticateUser(String username, String passwd) {
		// TODO Auto-generated method stub
		return null;
	}
	

	
}
