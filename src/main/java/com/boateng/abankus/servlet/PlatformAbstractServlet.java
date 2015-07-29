/**
 * hkboateng
 */
package com.boateng.abankus.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.User;
import com.boateng.abankus.employee.interfaces.AuthenticationService;
import com.boateng.abankus.employees.utils.EmployeeCollection;
import com.boateng.abankus.exception.PlatformException;

/**
 * @author hkboateng
 *
 */
public abstract class PlatformAbstractServlet {
	private static final Logger logger = LoggerFactory.getLogger(PlatformAbstractServlet.class);
	
	@Autowired(required=true)
	@Qualifier(value="authenticationServiceImpl")
	private AuthenticationService authenticationServiceImpl;	
	
	public void loadUserIntoSession(HttpServletRequest request) throws PlatformException{
		HttpSession session = request.getSession(false);
		String username = request.getUserPrincipal().getName();
		
		if(session.getAttribute("user") == null){
			User user = authenticationServiceImpl.findUserByUserName(username);
			session.setAttribute("user",user);
		}
	}
	
	public void loadEmployeeIntoSessionByUsername(HttpServletRequest request) throws PlatformException{
		HttpSession session = request.getSession(false);
		String username = request.getUserPrincipal().getName();
		
		if(session.getAttribute("employee") == null){
			//Loading User into Session
			Employee employee = authenticationServiceImpl.findEmployeeByUserName(username);
			EmployeeCollection.getInstance().addEmployee(username, employee);
			session.setAttribute("employee",employee);					
		}

	}
}
