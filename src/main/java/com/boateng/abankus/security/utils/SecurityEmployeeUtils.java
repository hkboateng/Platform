package com.boateng.abankus.security.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.boateng.abankus.domain.Employee;

public class SecurityEmployeeUtils {


	HttpSession session = null;
	
	
	/**
	 *  Default Constructor
	 */
	public SecurityEmployeeUtils() {
		// TODO Auto-generated constructor stub
	}



	
	
	public Employee getEmployeeInSession(HttpServletRequest request){
		session = request.getSession();
		Employee employee = (Employee) session.getAttribute("employee");
		return employee;
	}
}
