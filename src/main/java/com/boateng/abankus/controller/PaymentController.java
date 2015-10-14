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

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boateng.abankus.domain.CustomerOrder;
import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.OrderPayment;
import com.boateng.abankus.domain.Product;
import com.boateng.abankus.exception.PlatformException;
import com.boateng.abankus.fields.EmployeeFields;
import com.boateng.abankus.processors.CustomerOrderProcessor;
import com.boateng.abankus.processors.PaymentProcessor;
import com.boateng.abankus.processors.ProductServiceProcessor;
import com.boateng.abankus.services.ProductService;
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
public class PaymentController {

	private static final Logger logger = Logger.getLogger(PaymentController.class.getName());
	
	@Autowired
	private ProductServiceProcessor productServiceProcessor;
	@Autowired
	private PaymentProcessor paymentProcessor;
	@Autowired
	private CustomerOrderProcessor customerOrderProcessor;
	
	@RequestMapping(value = "/submitOrderPayment", method = RequestMethod.GET,produces="application/json")
	@ResponseBody
	public String submitOrderPayment(@Valid OrderPayment payment,HttpServletRequest request,Model model){
		
		String amount = request.getParameter("paymentAmount");
		String orderAmount = request.getParameter("orderTotalAmount");
		String orderNumber = request.getParameter("orderNumber");
		String typeOfPayment = request.getParameter("paymentSchedule");
		String formOfPayment = request.getParameter("paymentType");
		HttpSession session = request.getSession(false);
		
		Employee employee = (Employee) session.getAttribute(EmployeeFields.EMPLOYEE_SESSION);
		List<String> validation = paymentProcessor.validateOrderPayment(amount, orderAmount, orderNumber, formOfPayment, typeOfPayment);
		ObjectMapper mapper = new ObjectMapper();
		String status = "";

		
		try {
			if(!validation.isEmpty()){
				status = mapper.writeValueAsString(validation);
				return status;
			}
			status = paymentProcessor.submitPayment(amount, orderAmount, orderNumber, formOfPayment, typeOfPayment,employee);
			// mapper.writeValueAsString("good");
		} catch (JsonProcessingException e) {
			status = e.getMessage();
			logger.log(Level.WARNING,e.toString());
		} catch (PlatformException e) {
			status = e.getMessage();
			logger.log(Level.WARNING,e.toString());
		}
		return status;
	}
	
	@RequestMapping(value = "/makeCustomerOrderPayment", method = RequestMethod.POST)
	public String makeCustomerOrderPayment(HttpServletRequest request,Model model) throws NoSuchAlgorithmException, NoSuchPaddingException, PlatformException{
		String orderNumber = request.getParameter("orderNumber");
		String customerId= request.getParameter("customerId");
		String order = SecurityUtils.decryptOrderNumber(orderNumber);
		CustomerOrder customerOrder = customerOrderProcessor.findCustomerOrderByOrderNumber(order);


		if(customerOrder !=null){
			Product product = productServiceProcessor.findProductByProductCode(customerOrder.getProductCode());
			model.addAttribute("orderNumber", orderNumber);
			model.addAttribute("order", order);
			model.addAttribute("product",product);		
			model.addAttribute("cust",customerId);	
			model.addAttribute("customerOrder",customerOrder);	
		}

		
		return "ClientTransaction/CustomerPayment";
	}
	
	@RequestMapping(value = "/validateCustomerAuthenticate", method = RequestMethod.GET,produces="application/json")
	@ResponseBody
	public String validatePassword(@RequestParam(value="customerpin",required=true) String customerpin,@RequestParam(value="customerId",required=true) String customerId)
			  throws PlatformException, JsonProcessingException{
		String status = null;
		if(StringUtils.isBlank(customerpin) || !StringUtils.isAlphanumeric(customerpin)){
			
			status = "{error: Customer Pin is invalid}";
			logger.log(Level.WARNING,status);
			return status;
		}
		
		try{
		boolean note = productServiceProcessor.authenticatePasscode(customerpin,customerId);
			logger.info("Parsing response from Authenticating Passcode....to JSON");
			ObjectMapper mapper = new ObjectMapper();
			status =mapper.writeValueAsString(String.valueOf(note));
			logger.info("Parsing Authenticating Passcode response....to JSON completed.");
		}catch(PlatformException ace){
			logger.severe(ace.getMessage());
			status = "{error: "+ace.getMessage()+"}";
			throw ace;
			
		}
		return status;
	}
	
	
}
