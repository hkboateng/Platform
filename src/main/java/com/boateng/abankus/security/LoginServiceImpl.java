package com.boateng.abankus.security;

import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.User;
import com.boateng.abankus.exception.AbankusException;

public class LoginServiceImpl {

	private static final LoginServiceImpl login = new LoginServiceImpl();
	/**
	 * 
	 */
	private Employee employee;
	
	private LoginServiceImpl() {
	}

	public LoginServiceImpl(Employee employee) {
		if(employee !=null){
			this.employee = employee;
		}
	}

	public static LoginServiceImpl getInstance(){
		return login;
	}
	
	public User createLogin(String username, String password) throws AbankusException{
		User login = null;
		try {
			login = new User();
		} catch (Exception e) {
			AbankusException ace = new AbankusException(e);
			e.printStackTrace();
			throw ace;
		}
		
		return login;
	}
}
