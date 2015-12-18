/**
 * hkboateng
 */
package com.boateng.abankus.processors;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.client.ClientConfig;
import org.joda.time.DateTime;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import com.boateng.abankus.application.builders.CustomerPaymentBuilder;
import com.boateng.abankus.application.interfaces.CustomerService;
import com.boateng.abankus.application.interfaces.PlatformRequest;
import com.boateng.abankus.customer.processor.CustomerServiceProcessor;
import com.boateng.abankus.domain.Authenticatecustomer;
import com.boateng.abankus.domain.BankInformation;
import com.boateng.abankus.domain.Customer;
import com.boateng.abankus.domain.CustomerOrder;
import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.OrderPayment;
import com.boateng.abankus.domain.PaymentRequest;
import com.boateng.abankus.domain.PaymentTransaction;
import com.boateng.abankus.domain.Paymentmethod;
import com.boateng.abankus.exception.PlatformException;
import com.boateng.abankus.fields.EmployeeFields;
import com.boateng.abankus.services.CustomerOrderService;
import com.boateng.abankus.services.EmployeeService;
import com.boateng.abankus.services.PaymentService;
import com.boateng.abankus.servlet.PlatformAbstract;
import com.boateng.abankus.servlet.PlatformAbstractServlet;
import com.boateng.abankus.utils.SecurityUtils;
import com.boateng.abankus.utils.ValidationUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author hkboateng
 *
 */
public class PaymentProcessor extends PlatformAbstract{

	@Autowired
	private PaymentService paymentServiceImpl;
	
	@Autowired
	private CustomerOrderService customerOrderServiceImpl;
	
	@Autowired(required=true)
	private CustomerService customerServiceImpl;
	
	private static final Logger logger = Logger.getLogger(PaymentProcessor.class.getName());

	public PaymentProcessor(){}
	
	public String validatePaymentInput(String inputField){
		logger.info("Validating fields before search for Customer information..");
		String valid = null;
		if(inputField == null || inputField.isEmpty()){
			logger.info("Input field is empty or null");
			valid = "Input field is empty or null";
		}
		
		return valid;
	}
	
	public List<OrderPayment> searchPaymentListByCustomer(Customer customer){
		logger.finest("Employee is searching for Order and Payments with Customer Number: "+customer.getCustomerNumber());
		Integer customerId = customer.getCustomerId();
		List<OrderPayment> paymentList = paymentServiceImpl.findPaymentsByOrderId(customerId);
		if(paymentList.size() > 0){
			logger.finest("Search returned: "+paymentList.size()+" results.");
		}
		return paymentList;
	}
	public Customer searchPaymentDetail(String inputField){

		Integer customerId = null;
		Customer customer = null;
		try{
			customerId = Integer.parseInt(inputField);
			customer = customerServiceImpl.findCustomerByCustomerId(customerId);
		}catch(NumberFormatException e){
			logger.warning("Error occurred while converting "+inputField+" to an Integer.");
		}
		customerId = null;
		//Searching for Customer by Customer Number
		if(customer == null){
			logger.info("Staff with username: is search for Customer by Customer Number using value:"+inputField);
			customer = customerServiceImpl.findCustomerByCustomerNumber(inputField);
		}
		
		//Searching for Customer by Customer Account Number
		if(customer == null){
			logger.info("Staff with username: is search for Customer by Accout Number using value:"+inputField);
			customer = customerServiceImpl.findCustomerByAccountNumber(inputField);
		}
		
		return customer;
	}
	public String processPayment(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		
		Employee employee = (Employee) session.getAttribute(EmployeeFields.EMPLOYEE_SESSION);		
		String orderNumber = request.getParameter("orderNumber");	
		
		logActivity("is Processing a Payment for OrderNumber: "+orderNumber,employee);

			
		CustomerOrder order = customerOrderServiceImpl.findCustomerOrderByOrderNumber(orderNumber);	
			CustomerPaymentBuilder builder = new CustomerPaymentBuilder(request);

			OrderPayment orderPayment = builder.buildOrderPayment(order,employee);
			logActivity("is Processing a Payment for OrderNumber: "+orderNumber+". Payment amount of"+orderPayment.getAmountPaid(),employee);
			Paymentmethod paymentMethod = builder.buildPaymentMethod();
			BankInformation bank = builder.buildBankInformation();
			logger.info("Employee: "+employee.getEmployeeId()+ " is submitting a payment of: "+orderPayment.getAmountPaid()+" for order number: "+orderNumber);
			String status  = paymentServiceImpl.submitPayment(orderPayment,paymentMethod,bank);

			logger.info("Employee Id: "+employee.getEmployeeId()+ " has submitted a payment of: "+orderPayment.getAmountPaid()+" for order number: "+orderNumber+". Confirmation Number: "+status);
			
			return status;
	}
	public List<String> validateOrderPayment(String amount,String orderAmount,String orderNumber, String typeOfPayment, String formOfPayment){

		logger.log(Level.INFO, "Started validation for Order Number: "+orderNumber);
		List<String> errors = new ArrayList<String>();
				
		
				if(ValidationUtils.isNullOrBlank(amount)){
					errors.add("Amount to be paid cannot be null.");
				}
				if(ValidationUtils.isNullOrBlank(typeOfPayment)){
					errors.add("Type of Payment cannot be null");
				}
				if(ValidationUtils.isNullOrBlank(orderNumber)){
					errors.add("Amount to be paid cannot be null.");
				}		
				if(ValidationUtils.isNullOrBlank(formOfPayment)){
					errors.add("Form of Payment to be paid cannot be null.");
				}		
				
				String valids = comparePaymentAndAmountDue(amount,orderAmount);
				if(!valids.isEmpty()){
					errors.add(valids);
				}
				return errors;
	}

	
	public String comparePaymentAndAmountDue(String amountPaid,String orderAmount){
		double paidAmount = 0.0;
		double totalAmount = 0.0;
		StringBuilder sbr = new StringBuilder();
		try{
			totalAmount = Double.parseDouble(orderAmount);
			amountPaid = amountPaid.replace(",", "");
			paidAmount = Double.parseDouble(amountPaid);
			if(paidAmount > totalAmount){
				sbr.append("You cannot pay more than what you owe or more than the Order amount.");
			}
			
		}catch(NumberFormatException e){
			PlatformException ace = new PlatformException();
			ace.logger(Level.SEVERE, e.getMessage(), e);
			sbr.append("Amount Entered contains invalid characters!!. Please check and try again!!!.");
		}
		return sbr.toString();
	}
	
	public String submitPayment(String amount,String orderAmount,String orderNumber, String typeOfPayment, String formOfPayment,Employee employee) throws PlatformException{
		double paidAmount = 0.0;
		try{
			paidAmount = Double.parseDouble(amount);
			
			CustomerOrder order = customerOrderServiceImpl.findCustomerOrderByOrderNumber(orderNumber);

			OrderPayment orderPayment = new OrderPayment(paidAmount,employee,order,formOfPayment);
			
			Paymentmethod paymentMethod = new Paymentmethod();
			paymentMethod.setPaymentType(typeOfPayment);
			logger.info("Employee: "+employee.getEmployeeId()+ " is submitting a payment of: "+paidAmount+" for order number: "+orderNumber);
			String status  = paymentServiceImpl.submitPayment(orderPayment,paymentMethod,null);

			logger.info("Employee Id: "+employee.getEmployeeId()+ " has submitted a payment of: "+paidAmount+" for order number: "+orderNumber+". Confirmation Number: "+status);

			return status;
		}catch(Exception e){
			PlatformException ace = new PlatformException(e);
			throw ace;
		}
	}
	
	/**
	 * @param customerpin
	 */
	public boolean authenticatePasscode(String pin,String customerId)  throws PlatformException {
		logger.log(Level.INFO,"Platform is validating customer Pin Code.");
		Authenticatecustomer customer = null;
		boolean valid = false;
		try{
			if(StringUtils.isAlphanumeric(pin) && StringUtils.isNumeric(customerId)){
			
			int custId = Integer.parseInt(customerId);
			customer = customerServiceImpl.findCustomerById(custId);
				
			}else{
				return valid;
			}
			if(customer == null){
				return valid;
			}
			valid = isCustomerPasscodeValid(pin,customer.getPin());
			logger.info("Authenticating Customer results is: "+valid);
			return valid;
		}catch(Exception e){
			PlatformException ace = new PlatformException();
			logger.log(Level.WARNING,e.getMessage());
			throw ace;
		}

	}
	
	private boolean isCustomerPasscodeValid(String pin, String hashpin) throws PlatformException{
		String encodedPin = SecurityUtils.encryptOrderNumber(pin);
		return hashpin.contentEquals(encodedPin);
		//return SecurityUtils.authenticatePassword(pin, hashpin);
	}	
	
	private Paymentmethod buildPaymentMethod(HttpServletRequest request){
		Paymentmethod paymentMethod = new Paymentmethod();
		/**
		 * Whether payment is made in cash or CC or Bank
		 */
		String paymentType = request.getParameter("paymentMethod");
		String bankName = request.getParameter("bankName");
		String bankRoutingNumber = request.getParameter("bankRoutingNumber");
		String bankCustName = request.getParameter("bankCustName");
		String bankAccountNumber = request.getParameter("bankAccountNumber");
		String checkNumber = request.getParameter("checkNumber");
		
		paymentMethod.setPaymentType(paymentType);
		if(paymentType.equalsIgnoreCase("check")){
			
		}		
		if(paymentType.equalsIgnoreCase("card")){
			
		}
		return paymentMethod;
	}

	/**
	 * @param request
	 */
	public void submitPayment(HttpServletRequest request) {
		String amountPaid = request.getParameter("amountPaid");
		String paymentType = request.getParameter("paymentMethod");
		String orderNumber = request.getParameter("orderNumber");
		String customerId = request.getParameter("customerId");
		String orderId = request.getParameter("orderId");
	
		Paymentmethod paymentMethod = 	buildPaymentMethod(request);

		
	}

	/**
	 * @param orderNumber
	 * @param amount
	 * @param paymentMethod
	 */
	public List<String> validateBillPayment(String orderNumber, String amount, String paymentMethod) {
		logger.log(Level.INFO, "Started validation for Order Number: "+orderNumber);
		List<String> errors = new ArrayList<String>();
		if(orderNumber.isEmpty()){
			errors.add("Select any of the Bills for payment.");
		}
		if(amount.isEmpty()){
			errors.add("Enter the amount that Customer wants to pay.");
		}
		if(paymentMethod.isEmpty()){
			errors.add("Select a Payment Method");
		}
		
		return errors;
	}

	/**
	 * @param orderNumber - Order Number to apply the amount to.
	 * @param amount - Amount Paid
	 * @param paymentMethod - Payment method used
	 * @param employee - Staff submitting the Payment request
	 * @return 
	 */
	public PaymentTransaction submitBillPayment(Integer customerId,Integer orderNumber, String amount, String paymentMethod, Employee employee,HttpServletRequest request) {
		
		logger.info(logActivity(" is submitting payment amount: "+amount+ "to order number: "+orderNumber,employee));
		
		DateTime dateTime = new DateTime();

		amount = amount.replaceAll("[, ]", "");
		PaymentRequest paymentRequest = new PaymentRequest();
		paymentRequest.setAmountPaid(new BigDecimal(amount));
		paymentRequest.setOrderId(orderNumber);
		paymentRequest.setPaymentDate(dateTime.toString("MM/d/YYYY"));
		paymentRequest.setPaymentMethod(paymentMethod);
		paymentRequest.setCustomerId(customerId);
		paymentRequest.setEmployeeId(employee.getId());
		PaymentTransaction transaction = null;
		try {
			transaction = submitPaymentRequest(paymentRequest);
		} catch (PlatformException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
		return transaction;
	}
	
	private PaymentTransaction submitPaymentRequest(PaymentRequest request) throws  PlatformException{
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/paymenthub/paymentservice");
		AsyncInvoker invoke  = target.path("/saveCustomerPayment").request().async();
		ObjectMapper mapper = new ObjectMapper();
		
		PaymentTransaction transaction = null;
		try {
			Future<String> response = invoke.post(Entity.entity(mapper.writeValueAsString(request), MediaType.APPLICATION_JSON), String.class);
			String result = response.get();
			transaction = mapper.readValue(result, PaymentTransaction.class);
		} catch (InterruptedException | ExecutionException | IOException e) {
			PlatformException ace = new PlatformException();
			throw ace;
		}
		logger.info("Ok");;
		return transaction;
	}
}
