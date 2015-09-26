/**
 * hkboateng
 */
package com.boateng.abankus.controller;

import java.security.NoSuchAlgorithmException;

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

import com.boateng.abankus.domain.Product;
import com.boateng.abankus.exception.PlatformException;
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
	
	@RequestMapping(value = "/submitOrderPayment", method = RequestMethod.POST)
	public void submitOrderPayment(HttpServletRequest request,Model model){
		
	}
	
	@RequestMapping(value = "/makeCustomerOrderPayment", method = RequestMethod.GET)
	public String makeCustomerOrderPayment(HttpServletRequest request,Model model) throws NoSuchAlgorithmException, NoSuchPaddingException, PlatformException{
		String orderNumber = request.getParameter("orderNumber");
		String amount = request.getParameter("totalAmount");
		String orderDate = request.getParameter("orderDate");
		String productCode = request.getParameter("productCode");	
		
		//Decrypting Order Number
		String order = SecurityUtils.decryptOrderNumber(orderNumber);
		if(orderNumber != null && amount != null){
			logger.info("Starting to process payment for Order Number:"+ order+ " in the Amount of: "+amount);
		}
		 logger.info(order);
		double totalAmount = NumberFormatUtils.parseDoubleAmount(amount, ' ');
		if(StringUtils.isNotBlank(productCode) && StringUtils.isNotBlank(orderDate)){
			Product product = productServiceProcessor.findProductByProductCode(productCode);
			model.addAttribute("orderNumber", orderNumber);
			model.addAttribute("order", order);
			model.addAttribute("orderDate", orderDate);
			model.addAttribute("amount", totalAmount);
			model.addAttribute("product",product);			
		}

		
		return "ClientTransaction/CustomerPayment";
	}
}
