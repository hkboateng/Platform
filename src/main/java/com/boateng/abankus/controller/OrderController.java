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

import com.boateng.abankus.customer.processor.CustomerServiceProcessor;
import com.boateng.abankus.domain.CustomerAccount;
import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.Product;
import com.boateng.abankus.fields.EmployeeFields;
import com.boateng.abankus.processors.ProductServiceProcessor;
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
	
	@Autowired(required=true)
	private ProductServiceProcessor productServiceProcessor;	
	private HttpSession session;
	
	ModelAndView modelView = null;
	public OrderController(){
		
	}
	
	@RequestMapping(value = "/createOrders", method = RequestMethod.GET)
	public ModelAndView createOrder(){
		modelView = new ModelAndView();
		
		logger.info("Username: is viewing Create Order page.");
		List<Product> productList = productServiceProcessor.getAllProducts();
		modelView.addObject("productList", productList);
		modelView.setViewName("ClientServices/createOrders");
		return modelView;
	}
	
	@RequestMapping(value = "/findCustomer", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String findCustomer(@RequestParam(value="accountNumber",required=true) String accountNumber,HttpServletRequest request) throws IOException{
		session = request.getSession(false);
		
		Employee employee = (Employee) request.getSession(false).getAttribute(EmployeeFields.EMPLOYEE_SESSION);
		if(employee != null){
			logger.info("Employee ID: "+employee.getEmployeeId()+" is searching for Account Number: "+accountNumber);
		}
		
		CustomerAccount account = customerServiceProcessor.findCustomerAccountByAccountNumber(accountNumber);
		if(account == null){
			return "Account Number is invalid";
		}
		logger.info("Employee ID: "+employee.getEmployeeId()+" has found Customer with Account number: "+account.getAccountNumber()+" and Name: "+ account.getCustomer().getFirstname());

		ObjectMapper mapper = new ObjectMapper();
		logger.info("JSON has being convert..below is the Output...");
		mapper.writeValue(System.out, account);
		String acct = mapper.writeValueAsString(account);
	
		return acct;
	}

	
	@RequestMapping(value = "/addProductToCart", method = RequestMethod.GET, produces = "application/json")
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
	
	@RequestMapping(value = "/getNumberInChart", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public int getNumberInChart(HttpServletRequest request){
		session = request.getSession(false);
		int numberOfItems = 0;
		if(session.getAttribute("NUMBER_OF_ITEMS_IN_CART") != null){
			numberOfItems = (int) session.getAttribute("NUMBER_OF_ITEMS_IN_CART");
		}
		
		return numberOfItems;
	}
	
	@RequestMapping(value = "/addCustomerOrder", method = RequestMethod.POST)
	public void addCustomerOrder(HttpServletRequest request, Model model){
		
	}
	
	@RequestMapping(value = "/clientOrderDetail", method = RequestMethod.POST)
	public ModelAndView clientOrderDetail(HttpServletRequest request, Model model){
		logger.info("Employee is viewing Client Order Summary");
		modelView = new ModelAndView();
		modelView.setViewName("ClientServices/ClientOrderSummary");
		return modelView;
	}
	
	@RequestMapping(value = "/getProductDetails", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getProductDetails(@RequestParam(value="productCode",required=true) String productCode) throws JsonProcessingException{
		Product product = productServiceProcessor.findProductByProductCode(productCode);
		ObjectMapper mapper = new ObjectMapper();
		String productDetails = mapper.writeValueAsString(product);
		
		return productDetails;
	}

}
