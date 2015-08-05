package com.boateng.abankus.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boateng.abankus.customer.processor.CustomerServiceProcessor;
import com.boateng.abankus.customer.service.CustomerService;
import com.boateng.abankus.domain.Address;
import com.boateng.abankus.domain.Customer;
import com.boateng.abankus.domain.CustomerAccount;

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
	public String addCustomer(@Valid Customer customers,BindingResult result,HttpServletRequest request,Model model){
		if(result.hasErrors()){
			model.addAttribute("errors",result);
			return "redirect:/create/individual";
		}
		try {
			customerServiceProcessor.processNewCustomer(request);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("info", "Customer has being added successfully");
		return "redirect:/create";
	}
	
	@RequestMapping(value="/listCustomer", method=RequestMethod.GET)
	public String listCustomer(Model model,HttpServletRequest request){
		
		Set<Customer> customers = customerServiceProcessor.getAllCustomers();
		model.addAttribute("customers", customers);
		return "ClientServices/listCustomers";
	}
	
	@RequestMapping(value="/viewProfile", method=RequestMethod.GET)
	public String viewCustomerProfile(Model model,HttpServletRequest request,RedirectAttributes redirectAttributess){
		String customerId = request.getParameter("customerId");
		int Id = Integer.parseInt(customerId);
		if(customerId == null || Id < 1){
			model.addAttribute("message", "Customer Id cannot be Null or is invalid");
			return "ClientServices/ViewCustomerProfile";
		}
		
		//Find Customer Object
		Customer customer = customerServiceProcessor.findCustomerByCustomerId(Id);
		if(customer == null){
			model.addAttribute("message", "The Customer Identification you used is Invalid.");
			return "ClientServices/ViewCustomerProfile";
		}
		CustomerAccount customerAccount = customerServiceProcessor.findCustomerAccountByCustomerNumber(customer.getCustomerNumber());
		
		List<Address> address = customerServiceProcessor.findAddressByCustomerId(customer.getCustomerId());
		
		model.addAttribute("address",address);
		model.addAttribute("customerAccount",customerAccount);
		model.addAttribute("customer",customer);
		return "ClientServices/ViewCustomerProfile";
	}
}
