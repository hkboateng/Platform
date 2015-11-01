/**
 * hkboateng
 */
package com.boateng.abankus.processors;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.boateng.abankus.application.interfaces.CustomerService;
import com.boateng.abankus.customer.processor.CustomerServiceProcessor;
import com.boateng.abankus.domain.Authenticatecustomer;
import com.boateng.abankus.domain.CustomerOrder;
import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.OrderPayment;
import com.boateng.abankus.domain.Paymentmethod;
import com.boateng.abankus.exception.PlatformException;
import com.boateng.abankus.fields.EmployeeFields;
import com.boateng.abankus.services.CustomerOrderService;
import com.boateng.abankus.services.EmployeeService;
import com.boateng.abankus.services.PaymentService;
import com.boateng.abankus.utils.SecurityUtils;
import com.boateng.abankus.utils.ValidationUtils;

/**
 * @author hkboateng
 *
 */
public class PaymentProcessor {
	@Autowired
	private EmployeeService employeeServiceImpl;

	@Autowired
	private CustomerServiceProcessor customerServiceProcessor;

	@Autowired
	private PaymentService paymentServiceImpl;
	
	@Autowired
	private CustomerOrderService customerOrderServiceImpl;
	
	@Autowired(required=true)
	private CustomerService customerServiceImpl;
	
	private static final Logger logger = Logger.getLogger(PaymentProcessor.class.getName());

	
	private HttpSession session;
	
	public PaymentProcessor(){}

	
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
			PlatformException ace = new PlatformException(e);
			
			sbr.append("Amount Entered contains invalid characters!!. Please check and try again!!!.");
		}
		return sbr.toString();
	}
	
	public String submitPayment(String amount,String orderAmount,String orderNumber, String typeOfPayment, String formOfPayment,Employee employee) throws PlatformException{
		double paidAmount = 0.0;
		try{
			paidAmount = Double.parseDouble(amount);
			
			CustomerOrder order = customerOrderServiceImpl.findCustomerOrderByOrderNumber(orderNumber);

			OrderPayment orderPayment = new OrderPayment(paidAmount,employee,order);
			Paymentmethod paymentMethod = new Paymentmethod();
			/**
			 * Whether payment is made in cash or CC or Bank
			 */
			if(typeOfPayment.equalsIgnoreCase("cash")){
				paymentMethod.setPaymentType(typeOfPayment);
			}else if(formOfPayment.equalsIgnoreCase("bank")){
				
			}
			paymentMethod.setOrderNumber(order.getOrderNumber());
			logger.info("Employee: "+employee.getEmployeeId()+ " is submitting a payment of: "+paidAmount+" for order number: "+orderNumber);
			String status  = paymentServiceImpl.submitPayment(orderPayment,paymentMethod);

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
		Authenticatecustomer customer = null;
		boolean valid = false;
		try{
			if(StringUtils.isAlphanumeric(pin) && StringUtils.isNumeric(customerId)){
			logger.log(Level.INFO,"Platform is validating customer Pin Code.");
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
}
