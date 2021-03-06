/**
 * hkboateng
 */
package com.boateng.abankus.processors;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.boateng.abankus.application.interfaces.CustomerService;
import com.boateng.abankus.customer.processor.CustomerServiceProcessor;
import com.boateng.abankus.domain.BillingCollection;
import com.boateng.abankus.domain.Customer;
import com.boateng.abankus.domain.CustomerBilling;
import com.boateng.abankus.domain.CustomerOrder;
import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.OrderPayment;
import com.boateng.abankus.domain.Salesemployee;
import com.boateng.abankus.domain.factory.Factory;
import com.boateng.abankus.domain.factory.FactoryImpl;
import com.boateng.abankus.entity.validation.CustomerOrderUtils;
import com.boateng.abankus.exception.PlatformException;
import com.boateng.abankus.fields.EmployeeFields;
import com.boateng.abankus.services.CustomerOrderService;
import com.boateng.abankus.services.EmployeeService;
import com.boateng.abankus.services.OrderService;
import com.boateng.abankus.services.PaymentService;

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

	@Autowired
	private CustomerService customerServiceImpl;
	
	@Autowired
	private PaymentService paymentServiceImpl;
	
	public CustomerOrderProcessor(){
		//this.orderNumber = PlatformUtils.getClientOrderNumber();
	}
	public CustomerOrderProcessor(HttpServletRequest request){

	}
	
	public List<String> validateOrderRequest(HttpServletRequest request){
		logger.info("Validating Customer Order.....");
		CustomerOrderUtils customerOrderUtils = new CustomerOrderUtils(request);
		List<String> validation = customerOrderUtils.validateOrder();
		customerOrderUtils = null;
		return validation;
	}
	
	public List<String>  processClientOrder(HttpServletRequest request){
		CustomerOrderUtils customerOrderUtils = new CustomerOrderUtils(request);
		List<String> validation = customerOrderUtils.validateOrder();
		if(validation.size() > 0){
			return validation;
		}
		CustomerOrder customerOrder = null;
		try {
			customerOrder = orderService(request,"customerOrder");
		} catch (PlatformException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(customerOrder != null){
			validation.add("Customer Order has being saved successfully.");		
			employeeSales(request,customerOrder);
		}else{
			validation.add("Error occured while try to process your order.");	
		}
		customerOrder = null;
		customerOrderUtils = null;
		return validation;
	}
	/* (non-Javadoc)
	 * @see com.boateng.abankus.services.OrderService#orderService(com.boateng.abankus.domain.CustomerOrder)
	*/
	@Override
	public CustomerOrder orderService(HttpServletRequest request, String action) throws PlatformException {

		CustomerOrder customerOrder = (CustomerOrder) FactoryImpl.getFactory().construct(action, request);
		Integer customerId = 0;
		
		try{
			customerId = Integer.valueOf(request.getParameter("customerId"));
		}catch(NumberFormatException e){
			PlatformException ace = new PlatformException(e);
			throw ace;
		}
		RestTemplate rest = new RestTemplate();
		/**
		try {
			
			ResponseEntity<CustomerOrder> custOrder = rest.postForEntity("http://localhost:8080/paymenthub/orderservice/saveCustomerOrder?customerId="+customerId, customerOrder,CustomerOrder.class);
			if(custOrder.getStatusCode().value() == 200){
				customerOrder = custOrder.getBody();
			}
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};**/
		customerOrder = customerOrderServiceImpl.saveCustomerOrder(customerOrder,customerId);
		
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
			emp = null;
			return employeeSale;
		}
		return null;
	}
	
	public List<CustomerOrder>  loadAllOrderByCustomer(int customerId) throws PlatformException{
		if(customerId < 1){
			throw new PlatformException("Customer ID cannot be less than 1 or null.");
		}
		List<CustomerOrder>  orderList = customerOrderServiceImpl.findAllCustomerOrderByCustomerId(customerId);

		return orderList;
	}
	
	public BillingCollection getCustomerBillings(int customerId){
		List<CustomerOrder> orderList = null;
		BillingCollection collection = null;
		try {
			orderList = loadAllOrderByCustomer(customerId);
			if(orderList != null){
				collection = new BillingCollection(orderList);
				List<OrderPayment> paymentList = getAllPaymentByCustomerOrder(customerId);
				
				collection.setPaymentList(paymentList);
			}			
		} catch (PlatformException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return collection;
	}
	public Map<String, CustomerBilling> getCustomerBilling(int customerId){
		List<CustomerOrder> orderList = null;
		CustomerBilling billing = null;
		//Customer Number is Key, Value is a Map with order number as key
		Map<String,Map<String,CustomerBilling>> customerBillingMap = new HashMap<String,Map<String,CustomerBilling>>();
		//Order Number is key
		Map<String,CustomerBilling> customerOrderBillMap = new HashMap<String,CustomerBilling>();
		
		List<CustomerBilling> billingList = new ArrayList<CustomerBilling>();
		String orderNumber = null;
		String customerNumber = null;
		try {
			orderList = loadAllOrderByCustomer(customerId);
			if(orderList != null){
				
				for(CustomerOrder customerOrder: orderList){
					customerNumber = customerOrder.getCustomer().getCustomerNumber();
					orderNumber = customerOrder.getOrderNumber();
					billing = new CustomerBilling(customerOrder);
					List<OrderPayment> paymentList = findAllPaymentByOrderNumber(orderNumber);
					billing.setPayments(paymentList);
					billingList.add(billing);
					customerOrderBillMap.putIfAbsent(orderNumber, billing);
					customerBillingMap.putIfAbsent(customerNumber, customerOrderBillMap);				
				
				}
				
			}	
		} catch (PlatformException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		billingList = null;
		return customerBillingMap.get(customerNumber);
	}
	

	/**
	 * This method is used to retierve all customer Order's that are unpaid.
	 * @throws PlatformException 

	public void customerOrderPayment(HttpServletRequest request) throws PlatformException{
		String cust = request.getParameter("customerNumber");
		List<CustomerOrder> orderList = null;
		Customer customer = customerServiceImpl.findCustomerByCustomerNumber(cust);
		
		if(customer != null){
			 orderList = loadAllOrderByCustomer(customer.getCustomerId());
		}
	}
		 */
	public List<CustomerOrder>  loadAllOrderByCustomerNumber(String customerNumber) throws PlatformException{
		if(StringUtils.isBlank(customerNumber)){
			return null;
		}
		
		Customer customer = customerServiceImpl.findCustomerByCustomerNumber(customerNumber);
		
		if(customer !=null){
			List<CustomerOrder> orderList = customerOrderServiceImpl.findAllCustomerOrderByCustomerId(customer.getCustomerId());
			return orderList;
		}else{
			throw new PlatformException("There is no Customer information for the Customer Number : "+customerNumber+".");
		}
		
		
	}
	
	public CustomerOrder findCustomerOrderByOrderNumber(String orderNumber){
		if(StringUtils.isBlank(orderNumber.trim())){
			return null;
		}
		CustomerOrder customerOrder = customerOrderServiceImpl.findCustomerOrderByOrderNumber(orderNumber);
		return customerOrder;
	}

	@Override
	public List<OrderPayment> getAllPaymentByCustomerOrder(CustomerOrder order){
		List<OrderPayment> orderPayment =  null;
		if(order != null){
			orderPayment = paymentServiceImpl.findPaymentsByOrderId(order.getClientOrderId());
		}
		return orderPayment;
	}
	
	@Override
	public List<OrderPayment> getAllPaymentByCustomerOrder(int customerId){
		List<OrderPayment> orderPayment =  null;
		if(customerId > 0){
			orderPayment = paymentServiceImpl.findPaymentsByOrderId(customerId);
		}
		return orderPayment;
	}

	@Override
	public List<OrderPayment> findAllPaymentByOrderNumber(String orderNumber) {
		if(orderNumber== null || orderNumber.isEmpty()){
			return null;
		}
		CustomerOrder customerOrder = findCustomerOrderByOrderNumber(orderNumber);
		
		List<OrderPayment> orderPayment =  null;
		if(customerOrder != null){
			
			orderPayment = getAllPaymentByCustomerOrder(customerOrder);
		}
		
		return orderPayment;
	}
}
