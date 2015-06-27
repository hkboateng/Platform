package com.boateng.abankus.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;

import com.boateng.abankus.employee.interfaces.AuthenticationService;



public class LoginUser extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4677143436877140164L;

	@Autowired(required=true)
	@Qualifier(value="authenticationServiceImpl")
	private AuthenticationService authenticationServiceImpl;
	
	private String emailAddress;
	
	private String username;
	
	private Long  employeeId;
	
	private boolean enabled;
	
	private Employee employee;
	private Map<String, Set<Role>> userRoleMap = new HashMap<String, Set<Role>>();
	
	public LoginUser(Authentication auth) {
		
		this.username = auth.getName();
		this.employee = authenticationServiceImpl.findEmployeeByUserName(username);
		if(employee != null){
			this.emailAddress = employee.getEmail();
			this.employeeId = employee.getEmployeeId();
		}
		
		
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
	public Long getEmployeeId() {
		return employeeId;
	}


	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(Long employeeId) {
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


	public void put(String username, Role role){
		Set<Role> roleSet = null;
		if(userRoleMap.containsKey(username)){
			roleSet = userRoleMap.get(username);
		}else{
			roleSet = new HashSet<Role>();
		}
		roleSet.add(role);
		userRoleMap.put(username, roleSet);

	}

    public boolean hasMoreRole(){
    	
    	return userRoleMap.entrySet().iterator().hasNext();
    }
    
    public boolean userHasRole(String username,String role){
    	if(!userRoleMap.containsKey(username)){
    		return false;
    	}
    	for(Set<Role> roles: userRoleMap.values()){
    		if(roles.iterator().next().equals(role)){
    			return true;
    		}
    	}
    	
    	return false;
    }
	
}
