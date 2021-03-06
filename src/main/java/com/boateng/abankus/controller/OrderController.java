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
import com.boateng.abankus.domain.Customer;
import com.boateng.abankus.domain.CustomerAccount;
import com.boateng.abankus.domain.CustomerOrder;
import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.Product;
import com.boateng.abankus.exception.PlatformException;
import com.boateng.abankus.fields.CustomerFields;
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
	
	@RequestMapping(value = "/customer/createOrders", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView createOrder(HttpServletRequest request){
		logger.info("Username: is viewing Create Order page.");
		
		String accountNumber = request.getParameter("accountNumber");
		ModelAndView modelView = new ModelAndView();
		CustomerAccount account = null;
		
		if(accountNumber != null){
			account = customerServiceProcessor.findCustomerAccountByAccountNumber(accountNumber);
		}
		List<Product> productList = productServiceProcessor.getAllProducts();
		
		modelView.addObject("account", account);
		modelView.addObject("productList", productList);
		modelView.setViewName("ClientServices/createOrders");
		
		account = null;
		productList =  null;
		
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
	
	@RequestMapping(value = "/customer/findCustomer", method = RequestMethod.GET, produces = "application/json")
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

	/**
	
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
			numberOfItems = (Integer) session.getAttribute("NUMBER_OF_ITEMS_IN_CART");
		}
		
		return numberOfItems;
	}
	
	@RequestMapping(value = "/client/addCustomerOrder", method = RequestMethod.POST)
	public void addCustomerOrder(HttpServletRequest request, Model model){
		
	}
	**/
	@RequestMapping(value = "/client/clientOrderDetail", method = RequestMethod.POST)
	public ModelAndView clientOrderDetail(HttpServletRequest request, Model model){
		logger.info("Employee is viewing Client Order Summary");
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("ClientServices/ClientOrderSummary");
		return modelView;
	}
	
	@RequestMapping(value = "/customer/getProductDetails", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getProductDetails(@RequestParam(value="productCode",required=true) String productCode) throws JsonProcessingException{
		Product product = productServiceProcessor.findProductByProductCode(productCode);
		ObjectMapper mapper = new ObjectMapper();
		String productDetails = mapper.writeValueAsString(product);
		
		product = null;
		return productDetails;
	}

	@RequestMapping(value = "/order/submitCustomerOrder", method = RequestMethod.POST)
	public String orderSummary(HttpServletRequest request, Model model,RedirectAttributes redirectAttributess){
		HttpSession session = request.getSession(false);
		session.setAttribute("successMessageList", null);
		List<String> validation = customerOrderProcessor.processClientOrder(request);
		if(validation.size() > 0){
			session.setAttribute("successMessageList", validation);
			return "redirect:/customer/createOrders" ;
		}
		
		session.setAttribute("successMessageList", validation);
		validation = null;
		return "redirect:/customer/createOrders";
	}
	
	@RequestMapping(value = "/customer/orderHistory", method = RequestMethod.POST)
	public String orderHistory(HttpServletRequest request, Model model,RedirectAttributes redirectAttributess) throws PlatformException{
		logger.info("Viewing Customer Order history page.");
		Customer customer = (Customer) request.getSession(false).getAttribute(CustomerFields.CUSTOMER_SESSION);
	
			List<CustomerOrder> orderList = customerOrderProcessor.loadAllOrderByCustomer(customer.getCustomerId());
			model.addAttribute("customerOrder", orderList);
			model.addAttribute("customer",customer);
			BillingCollection collection = customerOrderProcessor.getCustomerBillings(customer.getCustomerId());
			session = request.getSession(false);
			session.setAttribute(CustomerOrderFields.BILLING_COLLECTION_SESSION, collection);
			model.addAttribute("billing", collection);
			
			collection = null;
			orderList = null;
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
		return view;
	}

	
	@RequestMapping(value = "/order/updateOrderDetails", method = RequestMethod.POST)
	public String updateOrderDetails(HttpServletRequest request,Model model) throws PlatformException{
		return "";
	}	
	
	@RequestMapping(value = "/order/editOrderDetails", method = RequestMethod.POST)
	public String editOrderDetails(HttpServletRequest request,Model model) throws PlatformException{
		return "";
	}	
}
