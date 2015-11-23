package com.boateng.abankus.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boateng.abankus.application.interfaces.CustomerService;
import com.boateng.abankus.customer.processor.CustomerServiceProcessor;
import com.boateng.abankus.domain.Address;
import com.boateng.abankus.domain.BillingCollection;
import com.boateng.abankus.domain.Customer;
import com.boateng.abankus.domain.CustomerAccount;
import com.boateng.abankus.domain.CustomerOrder;
import com.boateng.abankus.domain.Email;
import com.boateng.abankus.domain.OrderPayment;
import com.boateng.abankus.domain.PaymentTransaction;
import com.boateng.abankus.domain.Phone;
import com.boateng.abankus.exception.PlatformException;
import com.boateng.abankus.fields.CustomerOrderFields;
import com.boateng.abankus.processors.CustomerOrderProcessor;
import com.boateng.abankus.services.PaymentService;
import com.boateng.abankus.servlet.PlatformAbstractServlet;
import com.boateng.abankus.utils.SecurityUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Controller
//@RequestMapping("/customers")
public class CustomerController extends PlatformAbstractServlet{


	public static final String CUSTOMER_INDIVIDUAL = "individual";

	public static final String CUSTOMERS_COMPANY = "company";

	private static final Log log = LogFactory.getLog(CustomerController.class);
	
	@Autowired(required=true)
	@Qualifier(value="customerServiceImpl")
	private CustomerService customerServiceImpl;

	@Autowired(required=true)
	private CustomerServiceProcessor customerServiceProcessor;
	
	@Autowired(required=true)
	private CustomerOrderProcessor customerOrderProcessor;
	
	@Autowired(required=true)
	@Qualifier(value="paymentServiceImpl")
	private PaymentService paymentServiceImpl;	
	
	@PreAuthorize("isFullyAuthenticated")
	@RequestMapping(value="/customers/create", method=RequestMethod.GET)
	public String index(HttpServletRequest request){
		
		return "ClientServices/NewCustomer";
	}
	
	@PreAuthorize("isFullyAuthenticated")
	@RequestMapping(value="/customers/create/individual", method=RequestMethod.GET)
	public ModelAndView addIndividualCustomer(){
		ModelAndView model = new ModelAndView();
		
		model.setViewName("ClientServices/NewCustomer");
		return model;
	}
	
	
	@PreAuthorize("isFullyAuthenticated")
	@RequestMapping(value="/customers/create/company", method=RequestMethod.GET)
	public ModelAndView addCompanyCustomer(){
		ModelAndView model = new ModelAndView();
		model.setViewName("ClientServices/NewCustomer");
		return model;
	}
	
	@RequestMapping(value="/customers/addCustomer", method=RequestMethod.POST)
	public String addCustomer(@Valid Customer customers,BindingResult result,HttpServletRequest request,RedirectAttributes redirectAttributess){
		if(result.hasErrors()){
			redirectAttributess.addAttribute("errors",result);
			return "redirect:/create/individual";
		}
		try {
			customerServiceProcessor.processNewCustomer(request);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		redirectAttributess.addAttribute("success", UUID.randomUUID().toString());
		return "redirect:/platform/index";
	}
	
	@RequestMapping(value="/customers/listCustomer", method=RequestMethod.GET)
	public String listCustomer(Model model,HttpServletRequest request){
		
		Set<Customer> customers = customerServiceProcessor.getAllCustomers();
		model.addAttribute("customers", customers);
		return "ClientServices/listCustomers";
	}
	
	@RequestMapping(value="/customers/viewProfile", method={RequestMethod.GET,RequestMethod.POST})
	public String viewCustomerProfile(Model model,HttpServletRequest request) throws PlatformException{
		Customer customer = null;
		customer = getCustomerInSession(request);

			HttpSession session = request.getSession(false);
			String customerId = request.getParameter("customerId");
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String orderNumber = request.getParameter("orderNumber");
			String customerNumber = request.getParameter("customerNumber");
			String searchType=request.getParameter("searchType");
			
			if(searchType != null && searchType.equals("customerId")){
				customer = customerServiceProcessor.searchForCustomer(customerId);
			}else if(searchType != null && searchType.equals("customerName")){
				customer = customerServiceProcessor.searchForCustomerByFirstAndLastName(firstname, lastname);
			}else if(searchType != null && searchType.equals("order")){
				//search by order number, confirmation number or transaction Id
			}else if(searchType != null && !customerNumber.isEmpty() && searchType.equals("customerNumber")){
				customer = customerServiceProcessor.findCustomerByCustomerNumber(customerNumber);
			}
	
			if(customer == null){
				request.setAttribute("searchError", "Your Search did not return any results.");
				return "redirect:/platform/index";
			}
			
			loadCustomerIntoSession(request,customer);
			loadCustomerOrderHistory(model,customer.getCustomerId(),request);
			CustomerAccount customerAccount = customerServiceProcessor.findCustomerAccountByCustomerNumber(customer.getCustomerId());
			
			List<Address> address = customerServiceProcessor.findAddressByCustomerId(customer.getCustomerId());
			
			List<Phone> phone = customerServiceProcessor.findCustomerPhoneByCustomerId(customer.getCustomerId());
			
			List<Email> email = customerServiceProcessor.findCustomerEmailByCustomerId(customer.getCustomerId());
			
			session.setAttribute("address",address);
			session.setAttribute("customerAccount",customerAccount);
			model.addAttribute("customer",customer);
			model.addAttribute("phone",phone);
			model.addAttribute("email",email);

		return "ClientServices/ViewCustomerProfile";
	}
	
	private void loadCustomerOrderHistory(Model model,int customerId,HttpServletRequest request) throws PlatformException{
		HttpSession session = request.getSession(false);
		List<CustomerOrder> orderList = customerOrderProcessor.loadAllOrderByCustomer(customerId);
		model.addAttribute("customerOrder", orderList);

		BillingCollection collection = customerOrderProcessor.getCustomerBillings(customerId);

		session.setAttribute(CustomerOrderFields.BILLING_COLLECTION_SESSION, collection);
		model.addAttribute("billing", collection);		
	}
	@RequestMapping(value="/customers/isCustomerEmailUnique", method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public boolean isEmailUnique(@RequestParam(value="emailAddress",required=true) String emailAddress){
		log.info("Checking is Customer email is unique.");
		boolean isUnique = false;
		if(emailAddress != null || StringUtils.isAsciiPrintable(emailAddress)){
			//Checks if email is unique.
			Email customer = customerServiceProcessor.FindCustomerByEmailAddress(emailAddress);
			if(customer != null){
				isUnique = true;
			}
		}
		log.info("Customer is "+emailAddress+" is unique?: "+isUnique);
		return isUnique;
	}
	
	@RequestMapping(value="/customers/updateAccountStatus", method=RequestMethod.POST)
	public void updateCustomerAccountStatus(HttpServletRequest request){
		System.out.println("Updating Customer Account Status");
	}
	
	@RequestMapping(value="/customers/searchForCustomer", method=RequestMethod.GET)
	public String searchForCustomer(){
		return "ClientServices/CustomerSearch";
	}
	@RequestMapping(value = "/customers/makePayment", method = RequestMethod.POST)
	public String makePayment(HttpServletRequest request){
		
		return "ClientTransaction/CustomerPayment";
	}	
	
	@RequestMapping(value = "/customers/makePaymentSearch", method = RequestMethod.GET)
	public String makePaymentSearch(){
		return "ClientTransaction/CustomerPaymentSearch";
	}		
	
	@RequestMapping(value = "/platform/viewTransactionDetail", method = RequestMethod.POST)
	public String transactionDetails(HttpServletRequest request,Model model) throws PlatformException{
		String transactionId = request.getParameter("orderNumber");
		
		String orderNumber = SecurityUtils.decryptOrderNumber(transactionId);
		
		CustomerOrder order = customerOrderProcessor.findCustomerOrderByOrderNumber(orderNumber);
		
		List<OrderPayment> orderPayment = paymentServiceImpl.findPaymentsByOrderNumber(orderNumber);
		List<PaymentTransaction> payment = buildPaymentTransactionList(orderPayment);		
		
		model.addAttribute("customerOrder", order);
		model.addAttribute("orderNumber", orderNumber);
		model.addAttribute("paymentList", payment);
		return "ClientTransaction/TransactionDetails";
	}
	
	@RequestMapping(value = "/platform/loadPaymentsByOrderNumber", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public String loadPaymentsByOrderNumber(HttpServletRequest request){
		String orderNumber = request.getParameter("orderNumber");
		ObjectMapper mapper = new ObjectMapper();
		List<OrderPayment> orderPayment = paymentServiceImpl.findPaymentsByOrderNumber(orderNumber);
		List<PaymentTransaction> payment = buildPaymentTransactionList(orderPayment);
		
		String payments = null;
		try {
			mapper.writeValue(System.out, payment);
			payments = mapper.writeValueAsString(payment);
		} catch (IOException e) {
			payments = "error";
			e.printStackTrace();
		}
		return payments;
	}
	
	private List<PaymentTransaction> buildPaymentTransactionList(List<OrderPayment> orderPayment){
		
		ArrayList<PaymentTransaction> payments = new ArrayList<PaymentTransaction>();
		PaymentTransaction transaction = null;
		for(OrderPayment list: orderPayment){
			transaction = new PaymentTransaction(list);
			payments.add(transaction);
		}
		return payments;
	}
	public static JsonNode merge(JsonNode mainNode, JsonNode updateNode) {

	    Iterator<String> fieldNames = updateNode.fieldNames();
	    while (fieldNames.hasNext()) {

	        String fieldName = fieldNames.next();
	        JsonNode jsonNode = mainNode.get(fieldName);
	        // if field exists and is an embedded object
	        if (jsonNode != null && jsonNode.isObject()) {
	            merge(jsonNode, updateNode.get(fieldName));
	        }
	        else {
	            if (mainNode instanceof ObjectNode) {
	                // Overwrite field
	                String value = updateNode.get(fieldName).asText();
	                ((ObjectNode) mainNode).put(fieldName, value);
	            }
	        }

	    }

	    return mainNode;
	}
	
}
