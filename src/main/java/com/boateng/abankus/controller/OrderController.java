/**
 * hkboateng
 */
package com.boateng.abankus.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * This class contains methods and variables to accepting client/customers orders.
 * @author hkboateng
 *
 */

@Controller
@RequestMapping("/client")
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
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
	
	
}
