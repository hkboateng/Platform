package com.boateng.abankus.security.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.users.UserCollection;

public class SecurityEmployeeUtils {

	UserCollection userCollection = null;

	HttpSession session = null;
	
	
	/**
	 *  Default Constructor
	 */
	public SecurityEmployeeUtils() {
		// TODO Auto-generated constructor stub
	}


	public void addEmployeeInSession(HttpServletRequest request){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		session = request.getSession();
		userCollection = new UserCollection(auth);
		session.setAttribute("employee", userCollection);	
	}
	
	
	public Employee getEmployeeInSession(HttpServletRequest request){
		session = request.getSession();
		Employee employee = (Employee) session.getAttribute("employee");
		return employee;
	}
}
