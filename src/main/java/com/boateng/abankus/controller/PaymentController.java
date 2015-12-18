/**
 * hkboateng
 */
package com.boateng.abankus.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.boateng.abankus.domain.BillingCollection;
import com.boateng.abankus.domain.Customer;
import com.boateng.abankus.domain.CustomerBilling;
import com.boateng.abankus.domain.CustomerOrder;
import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.OrderPayment;
import com.boateng.abankus.domain.PaymentTransaction;
import com.boateng.abankus.domain.Product;
import com.boateng.abankus.domain.factory.FactoryImpl;
import com.boateng.abankus.exception.PlatformException;
import com.boateng.abankus.fields.CustomerFields;
import com.boateng.abankus.fields.CustomerOrderFields;
import com.boateng.abankus.fields.EmployeeFields;
import com.boateng.abankus.fields.PlatformFields;
import com.boateng.abankus.processors.CustomerOrderProcessor;
import com.boateng.abankus.processors.PaymentProcessor;
import com.boateng.abankus.processors.ProductServiceProcessor;
import com.boateng.abankus.services.ProductService;
import com.boateng.abankus.servlet.PlatformAbstract;
import com.boateng.abankus.utils.NumberFormatUtils;
import com.boateng.abankus.utils.SecurityUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author hkboateng
 *
 */
@Controller
@RequestMapping(value = "/Payments")
public class PaymentController extends PlatformAbstract  {

	private static final Logger logger = Logger.getLogger(PaymentController.class.getName());
	
	@Autowired
	private ProductServiceProcessor productServiceProcessor;
	@Autowired
	private PaymentProcessor paymentProcessor;
	@Autowired
	private CustomerOrderProcessor customerOrderProcessor;
	
	private HttpSession session;
	

	@RequestMapping(value = "/QuickPayment", method = RequestMethod.GET)
	public String payment(Model model){
		logger.info("Viewing Quick Payment");
		return "ClientTransaction/CustomerPaymentSearch";
	}

	@RequestMapping(value="/submitPayment", method=RequestMethod.POST)
	public void submitPayment(HttpServletRequest request,Model model){
		paymentProcessor.submitPayment(request);
	}
	@RequestMapping(value="/searchPaymentDetail", method=RequestMethod.POST)
	public String searchPaymentDetail(HttpServletRequest request,Model model) throws PlatformException{
		String searchInputField = request.getParameter("searchField");
		
		 String status = paymentProcessor.validatePaymentInput(searchInputField);
		 if(status != null){
			 model.addAttribute(PlatformFields.SEARCH_ERROR_SESSION, status);
			 logger.info(status);
			 return "ClientTransaction/CustomerPaymentSearch";
		 }
		 Customer customer = paymentProcessor.searchPaymentDetail(searchInputField);
		 List<CustomerOrder> customerOrderList = null;
		 if(customer != null){
			customerOrderList = customerOrderProcessor.loadAllOrderByCustomer(customer.getCustomerId());
			// List<OrderPayment>paymentProcessor.searchPaymentListByCustomer(customer);
			 model.addAttribute(PlatformFields.CUSTOMER_ORDER_LIST_SESSION, customerOrderList);
			 model.addAttribute(PlatformFields.CUSTOMER_SESSION, customer);
		 }
		 if(customer == null){
			
			 status = "No Information was found";
			 logger.info(status);
			 model.addAttribute(PlatformFields.SEARCH_ERROR_SESSION, status);
		 }
		customer = null;
		 customerOrderList = null;
		 
		return "ClientTransaction/CustomerPaymentSearch";
	}
	@RequestMapping(value = "/makeCustomerOrderPayment", method = RequestMethod.POST)
	public String makeCustomerOrderPayment(HttpServletRequest request,Model model) throws NoSuchAlgorithmException, NoSuchPaddingException, PlatformException{
		String order = request.getParameter("orderNumber");
		String customerId= request.getParameter("customerId");
		String orderNumber = SecurityUtils.decryptOrderNumber(order);
		session = request.getSession(false);
		
		BillingCollection collection = (BillingCollection) session.getAttribute(CustomerOrderFields.BILLING_COLLECTION_SESSION);
		if(collection !=null){
			CustomerBilling billing = collection.getCustomerBilling(orderNumber);
			Product product = productServiceProcessor.findProductByProductCode(billing.getClientOrderId().getProductCode());

			model.addAttribute("product",product);		
			model.addAttribute("customerId",customerId);	
			model.addAttribute("customerOrder",billing.getClientOrderId());	
			model.addAttribute("billing", billing);
		}

		
		return "ClientTransaction/CustomerPayment";
	}
	
	@RequestMapping(value = "/validateCustomerAuthenticate", method = RequestMethod.GET,produces="application/json")
	@ResponseBody
	public String validatePassword(HttpServletRequest request)  throws PlatformException, JsonProcessingException{
		String customerpin = request.getParameter("customerPIN");
		Customer customer = (Customer) request.getSession(false).getAttribute(CustomerFields.CUSTOMER_SESSION);
		
		String customerId = String.valueOf(customer.getCustomerId());
		String status = null;
		ObjectMapper mapper = new ObjectMapper();
		if(StringUtils.isBlank(customerpin) || !StringUtils.isAlphanumeric(customerpin)){
			
			status = "{error: Customer Pin is invalid}";
			logger.log(Level.WARNING,status);
			return mapper.writeValueAsString(status);
		}
		
		try{
			boolean note = paymentProcessor.authenticatePasscode(customerpin,customerId);
			logger.info("Parsing response from Authenticating Passcode....to JSON");

			status = mapper.writeValueAsString(String.valueOf(note));
			logger.info("Parsing Authenticating Passcode response....to JSON completed. "+ mapper.writeValueAsString(String.valueOf(note)));
			
			if(note){
				status = paymentProcessor.processPayment(request);
				status = mapper.writeValueAsString(status);
			}
		}catch(PlatformException ace){
			logger.severe(ace.getMessage());
			status = "{error: "+ace.getMessage()+"}";
			throw ace;
			
		}
		return status;
	}
	
	@RequestMapping(value = "/submitBillPayment", method = RequestMethod.POST)
	public String submitBillPayment(HttpServletRequest request,Model model) throws PlatformException{
		Employee employee = (Employee) request.getSession(false).getAttribute(EmployeeFields.EMPLOYEE_SESSION);
		String orderNumber = request.getParameter("orderNumber");
		String amount = request.getParameter("amountPaid");
		String paymentMethod = request.getParameter("paymentMethod");
		String customer = request.getParameter("customerId");
		
		logger.info(logActivity("is validating Billing Payment variables.",employee));
		List<String> validation = paymentProcessor.validateBillPayment(orderNumber,amount,paymentMethod);
		
		if(validation.size() > 0){
			model.addAttribute("billPaymentError", validation);
			return "";
		}
		Integer customerId =  null;
		Integer orderId = null;
		
		try{
			orderId = Integer.parseInt(orderNumber);
			customerId = Integer.parseInt(customer);
		}catch(NumberFormatException e){
			PlatformException ace = new PlatformException(e);
			throw ace;
		}
		logger.info(logActivity("is submitting Bill Paying for Order Number: "+orderNumber+" with payment amount: "+amount,employee));
		PaymentTransaction transaction = paymentProcessor.submitBillPayment(customerId,orderId,amount,paymentMethod,employee,request);
		validation = null;
		employee = null;
		
		request.setAttribute("paymentTransaction",transaction);
		transaction = null;
		return "ClientTransaction/BillPaymentConfirmation";
	}
	private String submitPayment(HttpServletRequest request) throws PlatformException{
		String status = null;
		
		String amount = request.getParameter("paymentAmount");
		String orderAmount = request.getParameter("orderTotalAmount");
		String orderNumber = request.getParameter("orderNumber");
		String typeOfPayment = request.getParameter("paymentSchedule");
		String formOfPayment = request.getParameter("paymentType");
		HttpSession session = request.getSession(false);
		
		Employee employee = (Employee) session.getAttribute(EmployeeFields.EMPLOYEE_SESSION);
		 paymentProcessor.processPayment(request);
		status = paymentProcessor.submitPayment(amount, orderAmount, orderNumber, formOfPayment, typeOfPayment,employee);
		return status;
	}
	
}
