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
import org.springframework.ui.Model;

import com.boateng.abankus.domain.Customer;
import com.boateng.abankus.domain.CustomerBilling;
import com.boateng.abankus.domain.CustomerOrder;
import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.Product;
import com.boateng.abankus.domain.User;
import com.boateng.abankus.employees.utils.EmployeeCollection;
import com.boateng.abankus.exception.PlatformException;
import com.boateng.abankus.fields.CustomerFields;
import com.boateng.abankus.fields.CustomerOrderFields;
import com.boateng.abankus.fields.EmployeeFields;
import com.boateng.abankus.fields.PlatformFields;
import com.boateng.abankus.processors.CustomerOrderProcessor;
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
	
	@Autowired(required=true)
	private CustomerOrderProcessor customerOrderProcessor;
	
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
	
	public void clearMessages(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		session.removeAttribute(PlatformFields.SEARCH_ERROR_SESSION);
		session.removeAttribute(PlatformFields.PRODUCT_LIST_SESSION);
		session.removeAttribute(PlatformFields.CUSTOMER_ORDER_LIST_SESSION);
		session.removeAttribute(PlatformFields.CUSTOMER_SESSION);
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
			cust = null;
		}
	}
	public void loadCustomerOrderHistory(Model model,int customerId,HttpServletRequest request) throws PlatformException{
		HttpSession session = request.getSession(false);
		session.setAttribute(CustomerOrderFields.BILLING_COLLECTION_SESSION,null);
		List<CustomerOrder> orderList = customerOrderProcessor.loadAllOrderByCustomer(customerId);
		model.addAttribute("customerOrder", orderList);

		Map<String, CustomerBilling> collection = customerOrderProcessor.getCustomerBilling(customerId);

		session.setAttribute(CustomerOrderFields.BILLING_COLLECTION_SESSION, collection);
		model.addAttribute("billing", collection);	
		orderList = null;
		collection = null;
	}
	
	public Customer getCustomerInSession(HttpServletRequest request)throws PlatformException{
		HttpSession session = request.getSession(false);
		Customer customer = (Customer) session.getAttribute(CustomerFields.CUSTOMER_SESSION);
		return customer;
	}
	
	public Employee getEmployeeInSession(HttpServletRequest request)throws PlatformException{
		HttpSession session = request.getSession(false);
		Employee employee = (Employee) session.getAttribute(EmployeeFields.EMPLOYEE_SESSION);
		return employee;
	}
	
	public String logActivity(String activity,Employee employee){
		StringBuilder sbr = new StringBuilder();
		if(employee != null){
			sbr.append("Staff ").append(employee.toString());
			sbr.append(" "+activity);			
		}
		return sbr.toString();
	}
}
