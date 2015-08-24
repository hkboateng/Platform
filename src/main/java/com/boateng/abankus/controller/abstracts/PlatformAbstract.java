/**
 * hkboateng
 */
package com.boateng.abankus.controller.abstracts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.fields.EmployeeFields;


/**
 * @author hkboateng
 *
 */
public abstract class PlatformAbstract {
	private static final Logger logger = LoggerFactory.getLogger(PlatformAbstract.class);
	
	private HttpSession session;
	
	private Employee employee;
	
	public void log(HttpServletRequest request){
		
	employee = (Employee) session.getAttribute(EmployeeFields.EMPLOYEE_SESSION);
	
	}
}
