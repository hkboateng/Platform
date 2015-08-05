package com.boateng.abankus.users;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.Role;
import com.boateng.abankus.domain.UserRole;
import com.boateng.abankus.services.AuthenticationService;

public class UserCollection{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1839835262666999631L;

	@Autowired(required=true)
	@Qualifier(value="authenticationServiceImpl")
	private AuthenticationService authenticationServiceImpl;
	
	private String emailAddress;
	
	private String username;
	
	private String  employeeId;
	
	private boolean enabled;
	
	private Employee employee;
	
	private Map<String, List<Role>> userRoleMap = new HashMap<String, List<Role>>();
	

	public UserCollection(){
		
	}
	public UserCollection(Authentication auth){
		String username = auth.getName();
		Employee employee = authenticationServiceImpl.findEmployeeByUserName(username);
		if(employee !=null){
			this.employeeId = employee.getEmployeeId();
			this.employee = employee;
		}
		List<Role> userRoleList = authenticationServiceImpl.findRoleByUser(username);
		if(!userRoleList.isEmpty()){
			userRoleMap.put(username, userRoleList);
		}
	}
	/**
	 * @param username
	 * @param password
	 * @param authorities
	 * @param emailAddress
	 * @param username2
	 * @param employeeId
	 * @param enabled
	 * @param employee
	 */
	public UserCollection(String username, String password,	Collection<? extends GrantedAuthority> authorities,	String emailAddress, String employeeId,	boolean enabled, Employee employee) {
		
		this.emailAddress = emailAddress;
		this.username = username;
		this.employeeId = employeeId;
		this.enabled = enabled;
		this.employee = employee;
	}

	private Map<String,UserDetails> userCollection = new LinkedHashMap<String,UserDetails>();

	/**
	 * @return the userCollection
	 */
	public Map<String, UserDetails> getUserCollection() {
		return userCollection;
	}

	/**
	 * @param userCollection the userCollection to set
	 */
	public void setUserCollection(Map<String, UserDetails> userCollection) {
		this.userCollection = userCollection;
	}
	
	public void addUserCollection(String username, UserDetails userDetails){
		if(username !=null && userDetails != null){
			userCollection.put(username, userDetails);
		}
	}
	
	public UserDetails getUser(String username){
		if(username == null || username.isEmpty()){
			return null;
		}
		
		if(!userCollection.containsKey(username)){
			return null;
		}
		
		return userCollection.get(username);
		
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
}
