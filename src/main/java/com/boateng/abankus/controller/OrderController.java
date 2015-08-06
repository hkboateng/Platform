/**
 * hkboateng
 */
package com.boateng.abankus.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.boateng.abankus.customer.processor.CustomerServiceProcessor;
import com.boateng.abankus.domain.CustomerAccount;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

/**
 * This class contains methods and variables to accepting client/customers orders.
 * @author hkboateng
 *
 */

@Controller
@RequestMapping("/client")
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired(required=true)
	private CustomerServiceProcessor customerServiceProcessor;
	
	public OrderController(){
		//do something
	}
	
	@RequestMapping(value = "/createOrders", method = RequestMethod.GET)
	public ModelAndView createOrder(){
		ModelAndView modelView = new ModelAndView();
		
		logger.info("Username: is viewing Create Order page.");
		modelView.setViewName("ClientServices/createOrders");;
		return modelView;
	}
	
	@RequestMapping(value = "/findCustomer", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String findCustomer(@RequestParam(value="accountNumber",required=true) String accountNumber) throws IOException{
		logger.info("Employee ID: XXXXX is searching for Account Number: "+accountNumber);
		CustomerAccount account = customerServiceProcessor.findCustomerAccountByCustomerNumber(accountNumber);
		logger.info("Employee ID:XXX has found User with Account number: "+ account.getCustomer().getFirstname());

		ObjectMapper mapper = new ObjectMapper();
		logger.info("JSON has being convert..below is the Output...");
		mapper.writeValue(System.out, account);
		String acct = mapper.writeValueAsString(account);
		
		return acct;
	}
}
