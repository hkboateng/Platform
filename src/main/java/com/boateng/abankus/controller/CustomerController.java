package com.boateng.abankus.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.boateng.abankus.customer.processor.CustomerServiceProcessor;
import com.boateng.abankus.domain.Customer;
import com.boateng.abankus.employee.interfaces.CustomerService;

@Controller
@RequestMapping("/customers")
public class CustomerController {

	private static final String CUSTOMER_TYPE = "customerType";

	public static final String CUSTOMER_INDIVIDUAL = "individual";

	public static final String CUSTOMERS_COMPANY = "company";

	@Autowired(required=true)
	@Qualifier(value="customerServiceImpl")
	private CustomerService customerServiceImpl;

	@Autowired(required=true)
	private CustomerServiceProcessor customerServiceProcessor;
	
	@PreAuthorize("isFullyAuthenticated")
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String index(HttpServletRequest request){
		
		return "ClientServices/NewCustomer";
	}
	
	@PreAuthorize("isFullyAuthenticated")
	@RequestMapping(value="/create/individual", method=RequestMethod.GET)
	public ModelAndView addIndividualCustomer(){
		ModelAndView model = new ModelAndView();
		model.addObject(CUSTOMER_TYPE,CUSTOMER_INDIVIDUAL);
		model.setViewName("ClientServices/NewCustomer");
		return model;
	}
	
	
	@PreAuthorize("isFullyAuthenticated")
	@RequestMapping(value="/create/company", method=RequestMethod.GET)
	public ModelAndView addCompanyCustomer(){
		ModelAndView model = new ModelAndView();
		model.addObject(CUSTOMER_TYPE,CUSTOMERS_COMPANY);
		model.setViewName("ClientServices/NewCustomer");
		return model;
	}
	
	@RequestMapping(value="/addCustomer", method=RequestMethod.POST)
	public String addCustomer( Customer customers,HttpServletRequest request){
		try {
			customerServiceProcessor.processNewCustomer(request);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
}
