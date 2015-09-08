/**
 * hkboateng
 */
package com.boateng.abankus.processors;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boateng.abankus.customer.processor.CustomerServiceProcessor;
import com.boateng.abankus.domain.Customer;
import com.boateng.abankus.domain.CustomerOrder;
import com.boateng.abankus.domain.factory.Factory;
import com.boateng.abankus.domain.factory.FactoryImpl;
import com.boateng.abankus.entity.validation.CustomerOrderUtils;
import com.boateng.abankus.service.impl.CustomerOrderServiceImpl;
import com.boateng.abankus.services.CustomerOrderService;
import com.boateng.abankus.utils.PlatformUtils;

/**
 * @author hkboateng
 *
 */

public class CustomerOrderProcessor {
	private static final Logger logger = LoggerFactory.getLogger(CustomerOrderProcessor.class);

	@Autowired
	private CustomerOrderService customerOrderServiceImpl;
	

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
		
		Factory factory = new FactoryImpl();
		CustomerOrder customerOrder = (CustomerOrder) factory.construct("customerOrder", request);
		try{
		Integer custId= 0;
		custId = Integer.valueOf(request.getParameter("customerId"));
		Customer customer = customerServiceProcessor.findCustomerByCustomerId(custId);
		customerOrder.setCustomer(customer);
		
		}catch(NumberFormatException e){
			throw e;
			//return validation;
		}
		customerOrderServiceImpl.saveCustomerOrder(customerOrder);
		validation.add("Customer Order has being saved successfully.");		
		return validation;
	}

}
