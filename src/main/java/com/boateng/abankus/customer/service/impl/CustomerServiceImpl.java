package com.boateng.abankus.customer.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.boateng.abankus.domain.Customer;
import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.services.AuthenticationService;

/**
 * This class is created when an Employee logs into the application. The Object is stored
 * in a session.
 * Basic information about the Employee is created here, stored in Session and used every where in the 
 * application.
 * @author hkboateng
 *
 */
public class CustomerServiceImpl {

	@Autowired(required=true)
	@Qualifier(value="authenticationServiceImpl")
	private AuthenticationService authenticationServiceImpl;
	
	private String firstname;
	
	private String lastname;
	public CustomerServiceImpl(){
		
	}
	
	public CustomerServiceImpl(String username){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		loadUserInformation(currentUserNameInSession(authentication));
		
	}
	
	private String currentUserNameInSession(Authentication auth){
		if(auth == null){
			return null;
		}
		if(!auth.isAuthenticated()){
			return null;
		}
		String username = auth.getName();
		return username;
	}
	
	private void loadUserInformation(String username){
		authenticationServiceImpl.findUserByUserName(username);
	}
}
