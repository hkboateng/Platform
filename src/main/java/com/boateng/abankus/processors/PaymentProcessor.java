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

import com.boateng.abankus.application.builders.CustomerPaymentBuilder;
import com.boateng.abankus.application.interfaces.CustomerService;
import com.boateng.abankus.application.interfaces.PlatformRequest;
import com.boateng.abankus.application.ws.svc.PaymentRequest;
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
import com.boateng.abankus.servlet.PlatformAbstractServlet;
import com.boateng.abankus.utils.SecurityUtils;
import com.boateng.abankus.utils.ValidationUtils;

/**
 * @author hkboateng
 *
 */
public class PaymentProcessor extends PlatformAbstractServlet{
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

	public String processPayment(HttpServletRequest request){
		String orderNumber = request.getParameter("orderNumber");	
		HttpSession session = request.getSession(false);
		
		Employee employee = (Employee) session.getAttribute(EmployeeFields.EMPLOYEE_SESSION);
			
			
		CustomerOrder order = customerOrderServiceImpl.findCustomerOrderByOrderNumber(orderNumber);	
			
			
			
			CustomerPaymentBuilder builder = new CustomerPaymentBuilder(request);
			PlatformRequest paymentRequest = new PaymentRequest(builder,employee.getEmployeeId());
			try {
				paymentRequest.buildRequest();
			} catch (PlatformException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			OrderPayment orderPayment = builder.buildOrderPayment(order,employee);
			Paymentmethod paymentMethod = builder.buildPaymentMethod();
			
			logger.info("Employee: "+employee.getEmployeeId()+ " is submitting a payment of: "+0.00+" for order number: "+orderNumber);
			String status  = paymentServiceImpl.submitPayment(orderPayment,paymentMethod);

			logger.info("Employee Id: "+employee.getEmployeeId()+ " has submitted a payment of: "+0.00+" for order number: "+orderNumber+". Confirmation Number: "+status);
			
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
			
			Paymentmethod paymentMethod = buildPaymentMethod(null);
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
	
	private Paymentmethod buildPaymentMethod(HttpServletRequest request){
		Paymentmethod paymentMethod = new Paymentmethod();
		/**
		 * Whether payment is made in cash or CC or Bank
		 */
		String typeOfPayment = request.getParameter("paymentSchedule");
		String bankName = request.getParameter("bankName");
		String bankRoutingNumber = request.getParameter("bankRoutingNumber");
		String bankCustName = request.getParameter("bankCustName");
		String bankAccountNumber = request.getParameter("bankAccountNumber");
		String checkNumber = request.getParameter("checkNumber");
		
		paymentMethod.setPaymentType(typeOfPayment);
		if(typeOfPayment.equalsIgnoreCase("check")){
			paymentMethod.setAccountnumber(bankAccountNumber);
			paymentMethod.setBankname(bankName);
			paymentMethod.setBanknumber(bankRoutingNumber);
			paymentMethod.setChecknumber(checkNumber);
			paymentMethod.setNameOnAccount(bankCustName);
		}		
		return paymentMethod;
	}
}
