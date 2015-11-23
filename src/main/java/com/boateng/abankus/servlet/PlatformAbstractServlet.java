/**
 * hkboateng
 */
package com.boateng.abankus.servlet;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.boateng.abankus.domain.Customer;
import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.Product;
import com.boateng.abankus.domain.User;
import com.boateng.abankus.employees.utils.EmployeeCollection;
import com.boateng.abankus.exception.PlatformException;
import com.boateng.abankus.fields.CustomerFields;
import com.boateng.abankus.fields.EmployeeFields;
import com.boateng.abankus.fields.PlatformFields;
import com.boateng.abankus.processors.ProductServiceProcessor;
import com.boateng.abankus.services.AuthenticationService;
import com.boateng.abankus.services.ProductService;

/**
 * @author hkboateng
 *
 */
public abstract class PlatformAbstractServlet {

	private static final Logger logger = Logger.getLogger(PlatformAbstractServlet.class.getName());
	
	@Autowired(required=true)
	@Qualifier(value="authenticationServiceImpl")
	private AuthenticationService authenticationServiceImpl;	
	
	@Autowired(required=true)
	@Qualifier(value="productServiceImpl")
	private ProductService productServiceImpl;

	@Autowired
	@Qualifier(value="productServiceProcessor")
	private ProductServiceProcessor productServiceProcessor;
	
	public void loadUserIntoSession(HttpServletRequest request) throws PlatformException{
		HttpSession session = request.getSession(false);
		String username = request.getUserPrincipal().getName();
		
		if(session.getAttribute("user") == null){
			logger.log(Level.FINEST,"User: "+username+" has being loaded into session.");
			User user = authenticationServiceImpl.findUserByUserName(username);
			session.setAttribute("user",user);
		}
	}
	
	public void loadEmployeeIntoSessionByUsername(HttpServletRequest request) throws PlatformException{
		HttpSession session = request.getSession(false);
		String username = request.getUserPrincipal().getName();
		
		if(session.getAttribute(EmployeeFields.EMPLOYEE_SESSION) == null){
			logger.info("Employee: "+username+" has being loaded into session.");
			
			//Loading User into Session
			Employee employee = authenticationServiceImpl.findEmployeeByUserName(username);
			EmployeeCollection.getInstance().addEmployee(username, employee);
			
			//employeeCollection.addEmployee(username, employee);
			session.setAttribute(EmployeeFields.EMPLOYEE_SESSION,employee);					
		}

	}
	
	/**
	 * Loads all of Products currently in the Database to a HashMap.
	 * 
	 */
	public void loadProductIntoMap(HttpServletRequest request){

		//productServiceProcessor.loadProductIntoSession();
	}
	
	/**
	 * Loads all of Products currently in the Database to a HashMap.
	 * 
	 */
	public void loadProductIntoSession(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(session.getAttribute(PlatformFields.PRODUCT_LIST_SESSION) == null){
			Map<String,Product> productList = productServiceProcessor.listAllProduct();
			session.setAttribute(PlatformFields.PRODUCT_LIST_SESSION, productList);			
		}

	}
	
	public void loadCustomerIntoSession(HttpServletRequest request,Customer customer){
		HttpSession session = request.getSession(false);
		if(session.getAttribute(CustomerFields.CUSTOMER_SESSION) == null){
			session.setAttribute(CustomerFields.CUSTOMER_SESSION, customer);
		}else{
			Customer cust = (Customer) session.getAttribute(CustomerFields.CUSTOMER_SESSION);
			if(!cust.getCustomerNumber().equals(customer.getCustomerNumber())){
				session.setAttribute(CustomerFields.CUSTOMER_SESSION, customer);
			}
		}
	}
	
	public Customer getCustomerInSession(HttpServletRequest request)throws PlatformException{
		HttpSession session = request.getSession(false);
		Customer customer = (Customer) session.getAttribute(CustomerFields.CUSTOMER_SESSION);
		return customer;
	}
}
