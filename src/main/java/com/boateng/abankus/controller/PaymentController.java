/**
 * hkboateng
 */
package com.boateng.abankus.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boateng.abankus.domain.BillingCollection;
import com.boateng.abankus.domain.Customer;
import com.boateng.abankus.domain.CustomerBilling;
import com.boateng.abankus.domain.CustomerOrder;
import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.PaymentTransaction;
import com.boateng.abankus.domain.Product;
import com.boateng.abankus.exception.PlatformException;
import com.boateng.abankus.fields.CustomerFields;
import com.boateng.abankus.fields.CustomerOrderFields;
import com.boateng.abankus.fields.EmployeeFields;
import com.boateng.abankus.fields.PlatformFields;
import com.boateng.abankus.processors.CustomerOrderProcessor;
import com.boateng.abankus.processors.PaymentProcessor;
import com.boateng.abankus.processors.ProductServiceProcessor;
import com.boateng.abankus.servlet.PlatformAbstractServlet;
import com.boateng.abankus.utils.PlatformUtils;
import com.boateng.abankus.utils.SecurityUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author hkboateng
 *
 */
@Controller
@RequestMapping(value = "/Payments")
public class PaymentController extends PlatformAbstractServlet  {

	private static final Logger logger = Logger.getLogger(PaymentController.class.getName());
	
	@Autowired
	private ProductServiceProcessor productServiceProcessor;
	@Autowired
	private PaymentProcessor paymentProcessor;
	@Autowired
	private CustomerOrderProcessor customerOrderProcessor;
	
	private HttpSession session;
	

	@Secured("ROLE_ACCEPT_PAYMENT")
	@RequestMapping(value = "/QuickPayment", method = RequestMethod.GET)
	public String payment(Model model){
		logger.info("Viewing Quick Payment");
		return "ClientTransaction/CustomerPaymentSearch";
	}

	@RequestMapping(value = "/ExternalPayment", method = RequestMethod.GET)
	public String externalpayment(Model model){
		logger.info("Viewing Quick Payment");
		return "ClientTransaction/QuickPayment";
	}
	
	@RequestMapping(value="/searchPaymentDetail", method=RequestMethod.POST)
	public String searchPaymentDetail(HttpServletRequest request,Model model,RedirectAttributes redirectAttributess) throws PlatformException{
		String searchInputField = request.getParameter("searchField");
		
		 String status = paymentProcessor.validatePaymentInput(searchInputField);

		 if(status != null){
			 redirectAttributess.addFlashAttribute(PlatformFields.SEARCH_ERROR_SESSION, status);
			 logger.info(status);
			 return "redirect:/Payments/QuickPayment";
		 }
		 Customer customer = paymentProcessor.searchPaymentDetail(searchInputField);
		 List<CustomerOrder> customerOrderList = null;
		 if(customer == null){
				
			 status = "No Information was found";
			 redirectAttributess.addFlashAttribute(PlatformFields.SEARCH_ERROR_SESSION, status);
			 return "redirect:/Payments/QuickPayment";
		 }
		 customerOrderList = customerOrderProcessor.loadAllOrderByCustomer(customer.getCustomerId());
		 if(customerOrderList.isEmpty()){
			 redirectAttributess.addFlashAttribute(PlatformFields.SEARCH_ERROR_SESSION, "No Order List is avaiable for Customer");
		 }else{
			 redirectAttributess.addFlashAttribute(PlatformFields.CUSTOMER_ORDER_LIST_SESSION, customerOrderList);
			 redirectAttributess.addFlashAttribute(PlatformFields.CUSTOMER_SESSION, customer);				
		 }
		customer = null;
		customerOrderList = null;
		 
		return "redirect:/Payments/QuickPayment";
	}
	
	@Secured("ROLE_ACCEPT_PAYMENT")
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/makeCustomerOrderPayment", method = RequestMethod.POST)
	public String makeCustomerOrderPayment(HttpServletRequest request,Model model,RedirectAttributes redirectAttributess) throws NoSuchAlgorithmException, NoSuchPaddingException, PlatformException{
		String order = request.getParameter("orderNumber");
		String customerId= request.getParameter("customerId");
		String orderNumber = SecurityUtils.decryptOrderNumber(order);
		session = request.getSession(false);
		
		Map<String, CustomerBilling>  collection = (Map<String, CustomerBilling>) session.getAttribute(CustomerOrderFields.BILLING_COLLECTION_SESSION);
		if(collection !=null){
			CustomerBilling billing = collection.get(orderNumber);
			Product product = productServiceProcessor.findProductByProductCode(billing.getClientOrderId().getProductCode());

			redirectAttributess.addFlashAttribute("product",product);		
			redirectAttributess.addFlashAttribute("customerId",customerId);	
			redirectAttributess.addFlashAttribute("customerOrder",billing.getClientOrderId());	
			redirectAttributess.addFlashAttribute("billing", billing);
		}else{
			redirectAttributess.addFlashAttribute("product","");
		}
		return "redirect:/Payments/makePayment";
	}
	
	@RequestMapping(value = "/validateCustomerAuthenticate", method = RequestMethod.GET,produces="application/json")
	@ResponseBody
	public String validatePassword(HttpServletRequest request)  throws PlatformException, JsonProcessingException{
		String customerpin = request.getParameter("customerPIN");
		Customer customer = getCustomerInSession(request);
		Employee employee = getEmployeeInSession(request);
		
		String customerId = String.valueOf(customer.getCustomerId());
		String status = null;
		PaymentTransaction transaction = null;
		try{
			if(StringUtils.isBlank(customerpin) || !StringUtils.isAlphanumeric(customerpin)){
				
				status = "{error: Customer Pin is invalid}";
				logger.log(Level.WARNING,status);
				status = PlatformUtils.convertToJSON(status);
			}else{
				boolean note = paymentProcessor.authenticatePasscode(customerpin,customerId,employee);
				logger.info("Parsing response from Authenticating Passcode....to JSON");

				status = PlatformUtils.convertToJSON(String.valueOf(note));
				logger.info("Parsing Authenticating Passcode response....to JSON completed. "+ PlatformUtils.convertToJSON(String.valueOf(note)));
				
				if(note){
					status = paymentProcessor.processPayment(request);
					//status = PlatformUtils.convertToJSON(status);
				}				
			}
		}catch(PlatformException ace){
			logger.severe(ace.getMessage());
			status = "{error: "+ace.getMessage()+"}";
			throw ace;
			
		} catch (IOException e) {
			PlatformException ace = new PlatformException("",e);
			logger.severe(ace.getMessage());
			status = "{error: "+ace.getMessage()+"}";
		}
		return status;
	}
	
	@Secured("ROLE_ACCEPT_PAYMENT")
	@RequestMapping(value = "/submitBillPayment", method = RequestMethod.GET,produces="application/json")
	@ResponseBody
	public String submitBillPayment(HttpServletRequest request,Model model) throws PlatformException{
		Employee employee = getEmployeeInSession(request);
		
		String orderNumber = request.getParameter("orderNumber");
		String amount = request.getParameter("amountPaid");
		String paymentMethod = request.getParameter("paymentMethod");
		String customer = request.getParameter("customer");
		String customerCode = request.getParameter("passcode");
		List<String> validation = null;
		String message = null;
		
		Integer customerId =  null;
		Integer orderId = null;
		
		try{
			logger.info(logActivity("has started the process of Submitting a payment transaction.",employee));
			validation = paymentProcessor.validateBillPayment(orderNumber,amount,paymentMethod);
			if(validation.size() > 0){
				return PlatformUtils.convertToJSON(validation);
			}		
			try{
				orderId = Integer.parseInt(orderNumber);
				customerId = Integer.parseInt(customer);
			}catch(NumberFormatException e){
				PlatformException ace = new PlatformException(e);
				throw ace;
			}
			Boolean note = paymentProcessor.authenticatePasscode(customerCode,customer,employee);	
			if(!note){
				try {
					message = PlatformUtils.convertToJSON(note);
					return message;
				} catch (IOException e) {
					PlatformException ace = new PlatformException(e);
					logger.info(e.getMessage());
					throw ace;
				}
			}		
			logger.info(logActivity("is submitting Bill Paying for Order Number: "+orderNumber+" with payment amount: "+amount,employee));
			
			String transaction = paymentProcessor.submitBillPayment(customerId,orderId,amount,paymentMethod,employee,request);
			
			logger.info(logActivity(" Payment sent has being completed.\n"+transaction,employee));		
			return transaction;	
		}catch (IOException e) {
			PlatformException ace = new PlatformException(e);
			logger.info(""+e.getMessage());
			throw ace;
		}finally{
			validation = null;
			employee = null;
			orderNumber = null;
			customer = null;
			customerCode = null;
			amount = null;
			paymentMethod = null;
		}	
	}

	@Secured("ROLE_ACCEPT_PAYMENT")
	@RequestMapping(value = "/makePayment", method = RequestMethod.GET)
	public String makePayment(HttpServletRequest request,Model model){
		
		return "ClientTransaction/CustomerPayment";
	}	
}
