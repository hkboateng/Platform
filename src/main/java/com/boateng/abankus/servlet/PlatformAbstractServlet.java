/**
 * hkboateng
 */
package com.boateng.abankus.servlet;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.Product;
import com.boateng.abankus.domain.User;
import com.boateng.abankus.employees.utils.EmployeeCollection;
import com.boateng.abankus.exception.PlatformException;
import com.boateng.abankus.fields.EmployeeFields;
import com.boateng.abankus.processors.ProductServiceProcessor;
import com.boateng.abankus.services.AuthenticationService;
import com.boateng.abankus.services.ProductService;

/**
 * @author hkboateng
 *
 */
public abstract class PlatformAbstractServlet {

	private static final Logger logger = LoggerFactory.getLogger(PlatformAbstractServlet.class);
	
	@Autowired(required=true)
	@Qualifier(value="authenticationServiceImpl")
	private AuthenticationService authenticationServiceImpl;	
	
	@Autowired(required=true)
	@Qualifier(value="productServiceImpl")
	private ProductService productServiceImpl;

	@Qualifier(value="productServiceProcessor")
	private ProductServiceProcessor productServiceProcessor;
	
	public void loadUserIntoSession(HttpServletRequest request) throws PlatformException{
		HttpSession session = request.getSession(false);
		String username = request.getUserPrincipal().getName();
		
		if(session.getAttribute("user") == null){
			logger.info("User: "+username+" has being loaded into session.");
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
	public void loadProductIntoMap(){
		Map<String, Product> productMap = productServiceProcessor.getProductMap();
		if(productMap.isEmpty()){
			List<Product> productList = null;
			productList = productServiceImpl.getAllProducts();
				/**
				 * Add Products from 'productList' to productMap. The Key is productCode and
				 * value is Product object.
				 */			
			for(Product product: productList){
				productMap.put(product.getProductCode(), product);
			}			
		}
	}
	

}
