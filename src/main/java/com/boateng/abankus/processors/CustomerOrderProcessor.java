/**
 * hkboateng
 */
package com.boateng.abankus.processors;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.boateng.abankus.customer.processor.CustomerServiceProcessor;
import com.boateng.abankus.domain.Customer;
import com.boateng.abankus.domain.CustomerOrder;
import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.Salesemployee;
import com.boateng.abankus.domain.factory.Factory;
import com.boateng.abankus.domain.factory.FactoryImpl;
import com.boateng.abankus.entity.validation.CustomerOrderUtils;
import com.boateng.abankus.fields.EmployeeFields;
import com.boateng.abankus.services.CustomerOrderService;
import com.boateng.abankus.services.EmployeeService;
import com.boateng.abankus.services.OrderService;
import com.boateng.abankus.utils.PlatformUtils;

/**
 * @author hkboateng
 *
 */

public class CustomerOrderProcessor implements OrderService{
	private static final Logger logger = LoggerFactory.getLogger(CustomerOrderProcessor.class);

	@Autowired
	private CustomerOrderService customerOrderServiceImpl;

	@Autowired
	private EmployeeService employeeServiceImpl;

	@Autowired
	private CustomerServiceProcessor customerServiceProcessor;
	
	private String quantity;
	
	private String productCode;
	
	private String productname;
	
	private String unitCost = null;
	
	private String customerId = null;
	
	private String orderNumber;
	
	
	public CustomerOrderProcessor(){
		this.orderNumber = PlatformUtils.getClientOrderNumber();
	}
	public CustomerOrderProcessor(HttpServletRequest request){

	}
	
	public List<String> validateOrderRequest(HttpServletRequest request){
		logger.info("Validating Customer Order.....");
		CustomerOrderUtils customerOrderUtils = new CustomerOrderUtils(request);
		List<String> validation = customerOrderUtils.validateOrder();

		return validation;
	}
	
	public List<String>  processClientOrder(HttpServletRequest request){
		CustomerOrderUtils customerOrderUtils = new CustomerOrderUtils(request);
		List<String> validation = customerOrderUtils.validateOrder();
		if(validation.size() > 0){
			return validation;
		}
		CustomerOrder customerOrder = orderService(request,"customerOrder");
		if(customerOrder != null){
			validation.add("Customer Order has being saved successfully.");		
			employeeSales(request,customerOrder);
		}else{
			validation.add("Error occured while try to process your order.");	
		}
		return validation;
	}
	/* (non-Javadoc)
	 * @see com.boateng.abankus.services.OrderService#orderService(com.boateng.abankus.domain.CustomerOrder)
	 */
	@Override
	public CustomerOrder orderService(HttpServletRequest request, String action) {
		Factory factory = new FactoryImpl();
		CustomerOrder customerOrder = (CustomerOrder) factory.construct(action, request);
		try{
		Integer custId= 0;
		custId = Integer.valueOf(request.getParameter("customerId"));
		Customer customer = customerServiceProcessor.findCustomerByCustomerId(custId);
		customerOrder.setCustomer(customer);
		
		}catch(NumberFormatException e){
			throw e;
			//return validation;
		}
		customerOrder = customerOrderServiceImpl.saveCustomerOrder(customerOrder);
		
		return customerOrder;
	}

	@Override
	public Salesemployee employeeSales(HttpServletRequest request,CustomerOrder customerOrder) {
		Employee emp = (Employee) request.getSession(false).getAttribute(EmployeeFields.EMPLOYEE_SESSION);
		Salesemployee employeeSale = new Salesemployee();
		if(emp != null){
			employeeSale.setEmployee(emp);
			employeeSale.setClientOrder(customerOrder.getClientOrderId());;
			employeeSale = employeeServiceImpl.saveEmployeeSales(employeeSale);
			
			return employeeSale;
		}
		return null;
	}

}
