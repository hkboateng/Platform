package com.boateng.abankus.service.impl;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.boateng.abankus.authentication.AuthenticationEmployeeResponse;
import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.Permission;
import com.boateng.abankus.domain.Role;
import com.boateng.abankus.domain.User;
import com.boateng.abankus.services.AuthenticationService;
import com.boateng.abankus.utils.PlatformUtils;
import com.boateng.abankus.utils.SecurityUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;

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

	@Transactional	
	@Override
	public User findUserByUserName(String username) {
		User query = (User)getSessionFactory().getCurrentSession()
				.createQuery("from User where username= :username")
				.setParameter("username", username)
				.uniqueResult();
		return query;
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
	
	
	@Override
	public Employee findEmployeeByUserName(String username) {
		Employee employee = null;
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/authenticationhub/authentication");
		Response response = target.path("/findEmployeeByUsername")
										.queryParam("username", username)
										.request(MediaType.APPLICATION_JSON)
										.get();
		
		String results = response.readEntity(String.class);
		try {
			AuthenticationEmployeeResponse employeeResponse = PlatformUtils.convertFromJson(new TypeReference<AuthenticationEmployeeResponse>() {}, results);
			if(employeeResponse.isResult()){
				employee = employeeResponse.getEmployee();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/** Finding Employee using username from User
		 * Query userQuery = getSessionFactory().getCurrentSession()
							.createQuery("from User where username= :username")
							.setParameter("username", username);
		User user = (User) userQuery.uniqueResult();
				Employee employee = (Employee) getSessionFactory().getCurrentSession()
								.createSQLQuery("select e from Employee e JOIN usrtbl u ON u.employeeId = e.employeeId where u.username= :username")
								.setParameter("username", username)
								.uniqueResult();
		 *  **/

		return employee;
	}
	/* (non-Javadoc)
	 * @see com.boateng.abankus.services.AuthenticationService#AuthenticateUser(java.lang.String, java.lang.String)
	 */
	@Override
	public User AuthenticateUser(String username, String passwd) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Permission> getEmployeePermissions() throws IOException{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/authenticationhub/authentication");
		Response response = target.path("/getEmployeeAllPermission")
										.request(MediaType.APPLICATION_JSON)
										.get();
		List<String> permissions = null;
		
		List<Permission> permissionList = null;
		String results= null;
		if(response.getStatus() == 200){
			results = response.readEntity(String.class);
			 permissionList = PlatformUtils.convertFromJson(new TypeReference<List<Permission>>() {}, results);
		}
		//List<Permission> permissionList = getAllUserPermission(permissions);
		return permissionList;
	}
	/**
	 * Removes hypen from the Strings
	 * @param permissionList
	 * @return
	 */
	private List<Permission> getAllUserPermission(List<String> permissionList){
		List<Permission> permissions = null;
		if(permissionList != null){
			permissions = new ArrayList<Permission>();
			for(String permission: permissionList){
				Permission p = new Permission();
				p.setPermission(permission.replaceAll("_", " "));
				permissions.add(p);
			}
		}
		return permissions;
	}
	
}
