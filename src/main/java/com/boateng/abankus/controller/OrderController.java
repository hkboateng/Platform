/**
 * hkboateng
 */
package com.boateng.abankus.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boateng.abankus.customer.processor.CustomerServiceProcessor;
import com.boateng.abankus.domain.BillingCollection;
import com.boateng.abankus.domain.CustomerAccount;
import com.boateng.abankus.domain.CustomerOrder;
import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.Product;
import com.boateng.abankus.exception.PlatformException;
import com.boateng.abankus.fields.CustomerOrderFields;
import com.boateng.abankus.fields.EmployeeFields;
import com.boateng.abankus.processors.CustomerOrderProcessor;
import com.boateng.abankus.processors.ProductServiceProcessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;

/**
 * This class contains methods and variables to accepting client/customers orders.
 * @author hkboateng
 *
 */

@Controller
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired(required=true)
	private CustomerServiceProcessor customerServiceProcessor;

	@Autowired(required=true)
	private CustomerOrderProcessor customerOrderProcessor;
	
	@Autowired(required=true)
	private ProductServiceProcessor productServiceProcessor;	
	
	private HttpSession session;
	
	public OrderController(){
		
	}
	
	@RequestMapping(value = "/client/createOrders", method = RequestMethod.GET)
	public ModelAndView createOrder(){
		ModelAndView modelView = new ModelAndView();
		
		logger.info("Username: is viewing Create Order page.");
		List<Product> productList = productServiceProcessor.getAllProducts();
		modelView.addObject("productList", productList);
		modelView.setViewName("ClientServices/createOrders");
		return modelView;
	}
	@RequestMapping(value = "/client/createCustomerOrder", method = RequestMethod.GET)
	public String createOrder(@RequestParam(value="accountNumber",required=true) String accountNumber,Model model){
	logger.info("Username: is viewing Create Order page.");
					
			CustomerAccount account = customerServiceProcessor.findCustomerAccountByAccountNumber(accountNumber);
			if(account == null){
				return "Account Number is invalid";
			}		
		List<Product> productList = productServiceProcessor.getAllProducts();
		model.addAttribute("productList", productList);
		model.addAttribute("account",account);
		return "ClientServices/createOrders";
	}	
	@RequestMapping(value = "/client/findCustomer", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String findCustomer(@RequestParam(value="accountNumber",required=true) String accountNumber,HttpServletRequest request) throws IOException{
		
				
		CustomerAccount account = customerServiceProcessor.findCustomerAccountByAccountNumber(accountNumber);
		if(account == null){
			return "Account Number is invalid";
		}
		ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true);
		logger.info("JSON has being convert..below is the Output...");
		mapper.writeValue(System.out, account);
		String acct = mapper.writeValueAsString(account);

		return acct;
	}

	
	@RequestMapping(value = "/client/addProductToCart", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String addProductToCart(@RequestParam(value="productCode",required=true) String productCode,@RequestParam(value="customerAccount",required=true) String customerAccount,HttpServletRequest request){
		session = request.getSession(false);
		Employee employee = (Employee) session.getAttribute(EmployeeFields.EMPLOYEE_SESSION);
		if(employee != null){
			logger.info("Employee ID: "+employee.getEmployeeId()+" is adding Product Code: "+productCode+" to Customer Chart");
		}		
		int numberOfItems =  getNumberInChart(request);
			numberOfItems +=1;
			session.setAttribute("NUMBER_OF_ITEMS_IN_CART", numberOfItems);
		
		
		productServiceProcessor.addProductsToChart(productCode,customerAccount, session);
		return String.valueOf(numberOfItems);
	}
	
	@RequestMapping(value = "/client/getNumberInChart", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public int getNumberInChart(HttpServletRequest request){
		session = request.getSession(false);
		int numberOfItems = 0;
		if(session.getAttribute("NUMBER_OF_ITEMS_IN_CART") != null){
			numberOfItems = (int) session.getAttribute("NUMBER_OF_ITEMS_IN_CART");
		}
		
		return numberOfItems;
	}
	
	@RequestMapping(value = "/client/addCustomerOrder", method = RequestMethod.POST)
	public void addCustomerOrder(HttpServletRequest request, Model model){
		
	}
	
	@RequestMapping(value = "/client/clientOrderDetail", method = RequestMethod.POST)
	public ModelAndView clientOrderDetail(HttpServletRequest request, Model model){
		logger.info("Employee is viewing Client Order Summary");
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("ClientServices/ClientOrderSummary");
		return modelView;
	}
	
	@RequestMapping(value = "/client/getProductDetails", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getProductDetails(@RequestParam(value="productCode",required=true) String productCode) throws JsonProcessingException{
		Product product = productServiceProcessor.findProductByProductCode(productCode);
		ObjectMapper mapper = new ObjectMapper();
		String productDetails = mapper.writeValueAsString(product);
		
		return productDetails;
	}

	@RequestMapping(value = "/client/submitCustomerOrder", method = RequestMethod.POST)
	public String orderSummary(HttpServletRequest request, Model model,RedirectAttributes redirectAttributess){
		List<String> validation = customerOrderProcessor.processClientOrder(request);
		if(validation.size() > 0){
			redirectAttributess.addAttribute("validation", validation);
			return "redirect:/client/createOrders" ;
		}
		//customerOrderProcessor.processClientOrder(request);
		return "redirect:/client/createOrders";
	}
	
	@RequestMapping(value = "/client/orderHistory", method = RequestMethod.POST)
	public String orderHistory(HttpServletRequest request, Model model,RedirectAttributes redirectAttributess) throws PlatformException{
		logger.info("Viewing Customer Order history page.");
		String custId = request.getParameter("customerId");
		int customerId = Integer.parseInt(custId);
		
			List<CustomerOrder> orderList = customerOrderProcessor.loadAllOrderByCustomer(customerId);
			model.addAttribute("customerOrder", orderList);
			model.addAttribute("customerId", custId);
			BillingCollection collection = customerOrderProcessor.getCustomerBillings(customerId);
			session = request.getSession(false);
			session.setAttribute(CustomerOrderFields.BILLING_COLLECTION_SESSION, collection);
			model.addAttribute("billing", collection);
			/**	
		BillingCollection collection = customerOrderProcessor.getCustomerBillings(customerId);
		model.addAttribute("customerOrder", collection);
		model.addAttribute("customerId", custId);
		***/
		return "ClientServices/OrderHistory";
	}
	
	@RequestMapping(value = "/order/findCustomerOrder", method = RequestMethod.GET ,produces = "application/json")
	@ResponseBody
	public String findCustomerOrder(@RequestParam(value="orderNumber",required=true) String orderNumber){
		
		return "";
	}

	@RequestMapping(value = "/order/findOrderByOrderNumber", method = RequestMethod.POST)
	public ModelAndView findOrderByOrderNumber(HttpServletRequest request, Model model,RedirectAttributes redirectAttributess){
		ModelAndView view = new ModelAndView();
		String customerNumber = request.getParameter("orderNumber");
		logger.info("Finding for Customer Order: "+customerNumber);
		List<CustomerOrder> orderList = null;
		try {
			orderList = customerOrderProcessor.loadAllOrderByCustomerNumber(customerNumber);
		} catch (PlatformException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(orderList == null){
			logger.info("Customer Number:"+ customerNumber+" did not return any result.");
			view.setViewName("");
			
			return  view;
		}
		
		view.addObject("orderList", orderList);
		view.setViewName("ClientTransaction/CustomerPaymentSearch");
		//getOrderByOrderNumber(model,orderList);
		return view;
	}

}
