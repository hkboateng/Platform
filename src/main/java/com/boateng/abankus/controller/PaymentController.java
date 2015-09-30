/**
 * hkboateng
 */
package com.boateng.abankus.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boateng.abankus.domain.CustomerOrder;
import com.boateng.abankus.domain.Product;
import com.boateng.abankus.exception.PlatformException;
import com.boateng.abankus.processors.CustomerOrderProcessor;
import com.boateng.abankus.processors.PaymentProcessor;
import com.boateng.abankus.processors.ProductServiceProcessor;
import com.boateng.abankus.services.ProductService;
import com.boateng.abankus.utils.NumberFormatUtils;
import com.boateng.abankus.utils.SecurityUtils;

/**
 * @author hkboateng
 *
 */
@Controller
@RequestMapping(value = "/Payments")
public class PaymentController {

	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
	@Autowired
	private ProductServiceProcessor productServiceProcessor;
	
	@Autowired
	private CustomerOrderProcessor customerOrderProcessor;
	
	@RequestMapping(value = "/submitOrderPayment", method = RequestMethod.POST)
	public String submitOrderPayment(HttpServletRequest request,Model model){
		PaymentProcessor processor = new PaymentProcessor();
		List<String> validation = processor.validateOrderPayment(request);
		if(!validation.isEmpty()){
			model.addAttribute("validation", validation);
			return "ClientTransaction/CustomerPayment";
		}
		return "";
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
	
	@RequestMapping(value = "/validatePasscode", method = RequestMethod.GET,produces="application/json")
	@ResponseBody
	public String validatePassword(@RequestParam(value="customerpin",required=true) String customerpin){
		if(StringUtils.isBlank(customerpin) || !StringUtils.isAlphanumeric(customerpin)){
			return "{error: Customer Pin is invalid}";
		}
		productServiceProcessor.authenticatePasscode(customerpin);
		return "";
	}
}
