/**
 * hkboateng
 */
package com.boateng.abankus.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boateng.abankus.application.interfaces.CustomerService;
import com.boateng.abankus.customer.processor.CustomerServiceProcessor;
import com.boateng.abankus.domain.Address;
import com.boateng.abankus.domain.BillingCollection;
import com.boateng.abankus.domain.Customer;
import com.boateng.abankus.domain.CustomerAccount;
import com.boateng.abankus.domain.CustomerBilling;
import com.boateng.abankus.domain.CustomerOrder;
import com.boateng.abankus.domain.CustomerTransaction;
import com.boateng.abankus.domain.Email;
import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.OrderPayment;
import com.boateng.abankus.domain.PaymentResponse;
import com.boateng.abankus.domain.PaymentSearchResponse;
import com.boateng.abankus.domain.PaymentTransaction;
import com.boateng.abankus.domain.Phone;
import com.boateng.abankus.domain.Product;
import com.boateng.abankus.exception.PlatformException;
import com.boateng.abankus.fields.CustomerOrderFields;
import com.boateng.abankus.fields.EmployeeFields;
import com.boateng.abankus.fields.PlatformFields;
import com.boateng.abankus.processors.PaymentProcessor;
import com.boateng.abankus.processors.ProductServiceProcessor;
import com.boateng.abankus.services.CustomerOrderService;
import com.boateng.abankus.services.EmployeeService;
import com.boateng.abankus.services.PaymentService;
import com.boateng.abankus.servlet.PlatformAbstractServlet;
import com.boateng.abankus.utils.PlatformConverter;
import com.boateng.abankus.utils.SecurityUtils;
import com.boateng.abankus.utils.ValidationUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author hkboateng
 *
 */

@Controller
public class PlatformController  extends PlatformAbstractServlet {
	
	/**
	 * 
	 */
	private static final String CUSTOMER_PAYMENT_SEARCH = "customerPaymentSearch";

	private static final String DATE_PAYMENT_SEARCH = "datePaymentSearch";
	
	@Autowired(required=true)
	@Qualifier(value="employeeServiceImpl")
	private EmployeeService employeeServiceImpl;	

	@Autowired(required=true)
	private CustomerOrderService customerOrderServiceImpl;	
	
	@Autowired(required=true)
	@Qualifier(value="paymentServiceImpl")
	private PaymentService paymentServiceImpl;	

	@Autowired(required=true)
	private CustomerService customerServiceImpl;	
	
	@Autowired(required=true)
	private CustomerServiceProcessor customerServiceProcessor;
	
	@Autowired(required=true)
	private PaymentProcessor paymentProcessor;
	
	@Autowired
	private ProductServiceProcessor productServiceProcessor;
	
	private static final Logger logger = Logger.getLogger(PlatformController.class.getName());

	
	@RequestMapping(value = "/platform/index", method = RequestMethod.GET)
	public String home(HttpServletRequest request, Model model,RedirectAttributes redirectAttributess) {
		try {
			//loadUserIntoSession(request);
			loadEmployeeIntoSessionByUsername(request);
			loadProductIntoSession(request);
		} catch (Exception e) {
			PlatformException ace  = new PlatformException();
			ace.logger(Level.WARNING,e.getMessage(), e);
			redirectAttributess.addFlashAttribute("errors", "Error occured authenticating you. Try again later.");
			return "redirect:/login";
		}
		return "dashboard/dashboard";
	}
	
	@RequestMapping(value = "/platform/settings", method = RequestMethod.GET)
	public String settings() {

		return "dashboard/Settings";
	}	
	
	@RequestMapping(value = "/platform/loadTodayTransactionHistory", method = RequestMethod.GET,produces="application/json")
	@ResponseBody
	public String loadTransactionHistory(HttpServletRequest request) throws PlatformException {
		HttpSession session = request.getSession(false);
		Employee employee = (Employee) session.getAttribute(EmployeeFields.EMPLOYEE_SESSION);
		int employeeId = employee.getId();
		DateTime dateTime = new DateTime();
		LocalDate localDate = dateTime.toLocalDate();
		
		DateTime today = localDate.toDateTimeAtStartOfDay();
		SimpleDateFormat d = new SimpleDateFormat("MM/dd/yyyy");
		dateTime.equals(d);
	
		List<OrderPayment> payments = employeeServiceImpl.findAllPaymentByEmployeeAndDate(employeeId);
		List<CustomerTransaction> transactionList = null;
		if(payments !=null && !payments.isEmpty()){
			transactionList = new ArrayList<CustomerTransaction>();
			CustomerTransaction transaction = null;
			for(OrderPayment p: payments){
				d.format(p.getPaymentDate());
				if(!today.isAfter(p.getPaymentDate().getTime())){
					transaction = new CustomerTransaction(p);
					transactionList.add(transaction);					
				}

			}
			transaction = null;
		}
		String results = null;
		try {
			results = PlatformConverter.convertTransactionToString(transactionList);
			
		} catch (IOException e) {
			PlatformException ace = new  PlatformException(e);
			
		}finally{
			transactionList = null;
		}
		
		
		return results;
		
	}

	/**
	@RequestMapping(value = "/platform/customerPaymentSearch", method = RequestMethod.POST)
	public String searchCustomerPayment(HttpServletRequest request,Model model,RedirectAttributes redirectAttributess){
		String results = null;
		
		String customerId = request.getParameter("customerId");
		String custTransactionFrom = request.getParameter("custTransactionFrom");
		String custTransactionTo = request.getParameter("custTransactionTo");
		String empTransactionFrom = request.getParameter("empTransactionFrom");
		String empTransactionTo = request.getParameter("empTransactionTo");
		String emp = request.getParameter("employee");
		String searchType=request.getParameter("searchType");
		
		if(searchType.equals("customerPayments")){
			
		}
		return results;
	}
	**/
	@RequestMapping(value = "/platform/customerProfileSearch", method = {RequestMethod.POST,RequestMethod.GET})
	public String searchDashBoard(HttpServletRequest request,Model model,RedirectAttributes redirectAttributess) throws PlatformException{
		String redirect = null;
		String firstname = request.getParameter("firstName");
		String lastname = request.getParameter("lastName");
		String customerId = request.getParameter("customerIdentity");
		String searchType=request.getParameter("searchType");
		
		logger.info("Search for Customer with Customer Number: "+customerId);
		Customer customer = null;
		List<Customer> customerList = null;
		if(searchType.equals("customerIdentity")){
			if((customerId != null && !customerId.isEmpty())){
				customer = findCustomerIdentity(customerId);
			}		
		}else if(searchType.equals("customerDetail")){
			customerList = findAllCustomerByFirstNameAndLastName(firstname,lastname);
		}
		if(customerList != null){
			redirectAttributess.addFlashAttribute("customerList",customerList);
			redirect = "redirect:/customers/searchForCustomer";
		}
		if(customer == null || customerList == null){
			redirectAttributess.addFlashAttribute("searchError", "No information was found, Try again.");
			redirect = "redirect:/customers/searchForCustomer";			
						
		}		
		//The results did not return any results
		if(customer != null){
			loadCustomerIntoSession(request,customer);
			loadCustomerOrderHistory(model,customer.getCustomerId(),request);	
			CustomerAccount customerAccount = customerServiceProcessor.findCustomerAccountByCustomerNumber(customer.getCustomerId());
	
			model.addAttribute("customerAccount",customerAccount);
			model.addAttribute("customer",customer);
			redirect = "ClientServices/ViewCustomerProfile";
		}
		return 	redirect;
	}

	/**
	 * @param firstname
	 * @param lastname
	 * @return
	 */
	private List<Customer> findAllCustomerByFirstNameAndLastName(String firstname, String lastname) {
		List<Customer> customers = null;
		if(ValidationUtils.isAlpha(firstname) && ValidationUtils.isAlpha(lastname)){
			customers = customerServiceImpl.findCustomerLikeFirstNameAndLastName(firstname, lastname);
		}
		return customers;
	}
	/**
	 * @param customerIdentity
	 * @return
	 */
	private Customer findCustomerIdentity(String customerIdentity) {
		Customer customer = null;
		if(ValidationUtils.isValid(customerIdentity) && ValidationUtils.isEmailValid(customerIdentity)){
			customer = customerServiceImpl.findCustomerByEmail(customerIdentity);
		}else if(ValidationUtils.isValid(customerIdentity) && ValidationUtils.isAlphaNumeric(customerIdentity)){
			customer = customerServiceProcessor.findCustomerByCustomerNumber(customerIdentity);
			if(customer == null){
				try{
					Integer customerId = Integer.parseInt(customerIdentity);
					customer = customerServiceProcessor.findCustomerByCustomerId(customerId);
				}catch(NumberFormatException e){
					logger.warning("Customer Id:"+customerIdentity+" is throwing an error while parse it to an Integer.");
					logger.warning(e.getMessage());
				}
			}
		}
		return customer;
	}
	@RequestMapping(value = "/platform/loadPayments", method = RequestMethod.GET,produces="application/json")
	@ResponseBody
	public String loadPayments(HttpServletRequest request){
		String date = request.getParameter("date");
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<PaymentTransaction> payments = paymentServiceImpl.findAllPaymentsByDate(date);
		String status = null;
		try {
			status = mapper.writeValueAsString(payments);
		} catch (JsonProcessingException e) {
			logger.warning(e.getMessage());
		}
		return status;
	}
	@RequestMapping(value = "/platform/loadMonthPayments", method = RequestMethod.GET,produces="application/json")
	@ResponseBody
	public String loadMonthPayments(HttpServletRequest request){
		String month = request.getParameter("month");
		String year = request.getParameter("year");
		ObjectMapper mapper = new ObjectMapper();
		
		List<PaymentTransaction> payments = paymentServiceImpl.findAllMonthPaymentByYearAndMonth(year, month);
		String status = null;
		try {
			status = mapper.writeValueAsString(payments);
		} catch (JsonProcessingException e) {
			logger.warning(e.getMessage());
		}
		return status;
	}
	
	@RequestMapping(value = "/platform/loadYearTransactionHistory", method = RequestMethod.GET,produces="application/json")
	@ResponseBody
	public String loadYearlyTransactionHistry(){
		DateTime dateTime = new DateTime();
		String year = String.valueOf(dateTime.getYear());
		List<PaymentTransaction> payments = paymentServiceImpl.findAllYearPaymentByYear(year);
		ObjectMapper mapper = new ObjectMapper();
		String status = null;
		try {
			status = mapper.writeValueAsString(payments);
		} catch (JsonProcessingException e) {
			logger.warning(e.getMessage());
		}
		return status;
	}	

	
	@RequestMapping(value = "/platform/viewTransactionHistory", method = RequestMethod.POST)
	public String transactionHistory(HttpServletRequest request,Model model){
		String customerNumber = request.getParameter("customerNumber");
		logger.info("Search for Transaction History for Customer Number: "+customerNumber);
		PaymentTransaction payment = paymentServiceImpl.findPaymentTransactionByTransactionId(customerNumber);
		model.addAttribute("transaction", payment);
		return "ClientTransaction/TransactionHistory";
	}	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/platform/viewTransactionDetail", method = RequestMethod.POST)
	public String transactionDetails(HttpServletRequest request,Model model) throws PlatformException{
		String orderNumber = request.getParameter("orderNumber");
		logger.info("Searching for Transaction Details for Order Number: "+orderNumber);
		HttpSession session = request.getSession(false);
		
		orderNumber = SecurityUtils.decryptOrderNumber(orderNumber);
		Map<String, CustomerBilling> collection = (Map<String, CustomerBilling>) session.getAttribute(CustomerOrderFields.BILLING_COLLECTION_SESSION);
		
		List<PaymentTransaction> payment = null;
		CustomerBilling billing = null;
		List<OrderPayment> orderPayment = null;
		if(collection != null){
			billing = collection.get(orderNumber);
			orderPayment = billing.getPayments();
		}	
		if(orderPayment !=null){
			payment = buildPaymentTransactionList(orderPayment); 
		}
		model.addAttribute("paymentList", payment);
		model.addAttribute(PlatformFields.VIEW_TRANSACTION_DETAILS_ORDER_NUMBER, orderNumber);
		model.addAttribute("billing", billing);
		
		payment = null;
		billing = null;	
		orderPayment = null;
		
		return "ClientTransaction/TransactionDetails";
	}
	private List<PaymentTransaction> buildPaymentTransactionList(List<OrderPayment> orderPayment){
		
		ArrayList<PaymentTransaction> payments = new ArrayList<PaymentTransaction>();
		PaymentTransaction transaction = null;
		Product product = null;
		for(OrderPayment payment: orderPayment){
			transaction = new PaymentTransaction(payment);
			product = productServiceProcessor.findProductByProductCode(payment.getClientorder().getProductCode());
			transaction.setProductName(product.getProductName());
			payments.add(transaction);
		}
		product = null;
		transaction = null;
		return payments;
	}
	

	
	@RequestMapping(value = "/platform/loadCustomerOrderByOrderNumber", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public String loadCustomerOrderByOrderNumber(HttpServletRequest request){
		String orderNumber = request.getParameter("orderNumber");
		ObjectMapper mapper = new ObjectMapper();
		CustomerOrder customerOrder = customerOrderServiceImpl.findCustomerOrderByOrderNumber(orderNumber);
		
		String payments = null;
		try {
			mapper.writeValue(System.out,customerOrder);
			payments = mapper.writeValueAsString(customerOrder);
		} catch (IOException e) {
			payments = "error";
			logger.log(Level.WARNING,e.getMessage(),e);
		}
		return payments;
	}
	
	@RequestMapping(value = "/platform/loadPaymentsByOrderNumber", method = RequestMethod.GET,produces="application/json")
	@ResponseBody
	public String loadPaymentsByOrderNumber(HttpServletRequest request){
		String orderNumber = request.getParameter("orderNumber");
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<PaymentTransaction> payments = paymentServiceImpl.findPaymentTransactionByOrderNumber(orderNumber);
		String status = null;
		try {
			status = mapper.writeValueAsString(payments);
		} catch (JsonProcessingException e) {
			logger.warning(e.getMessage());
		}
		return status;
	}
	@RequestMapping(value = "/platform/countTotalCustomers", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Integer countTotalCustomers(){
		int total = customerServiceImpl.countTotalCustomer();
		
		return total;
	}
	
	@RequestMapping(value="/platform/customerPaymentSearch", method = RequestMethod.POST, produces = "application/json" )
	public String customerPaymentSearch(HttpServletRequest request,Model model,RedirectAttributes redirectAttributess) throws PlatformException{
		String customerNumber = request.getParameter("customerNumber");
		String from = request.getParameter("transactionFrom");
		String to = request.getParameter("transactionTo");
		
		Employee employee = getEmployeeInSession(request);
		logger.info(logActivity(" is submitting a Payment Search for Customer Number: "+customerNumber+" for Date from: "+from+" to: "+to,employee));
		
		String redirect = "redirect:/customers/searchForCustomer";
		String action = PlatformController.CUSTOMER_PAYMENT_SEARCH;
		List<PaymentSearchResponse> response = paymentProcessor.submitPaymentSearchRequest(customerNumber,from,to,action);
		if(response != null){
			redirectAttributess.addFlashAttribute("customerSearchPayment", response);
			
		}else{
			redirectAttributess.addFlashAttribute(PlatformFields.PAYMENT_SEARCH_MESSAGE, "No Payment search results was returned for Customer Number: "+customerNumber+" Date from:"+from+" to: "+to);
		}
			action = null;
			from = null;
			to = null;
			customerNumber = null;
		return  redirect;
	}
	
	@RequestMapping(value="/platform/platformSearch", method = RequestMethod.GET)
	public String platformCustomerSearch(){
		return "ClientServices/CustomerSearch";
	}
	
	@RequestMapping(value="/platform/datePaymentSearch", method = RequestMethod.POST)
	public String datePaymentSearch(HttpServletRequest request,Model model,RedirectAttributes redirectAttributess){
		String customerNumber = request.getParameter("customerNumber");
		String from = request.getParameter("transactionFrom");
		String to = request.getParameter("transactionTo");
		
		String redirect = "redirect:/customers/searchForCustomer";
		String action = PlatformController.DATE_PAYMENT_SEARCH;
		List<PaymentSearchResponse> response = paymentProcessor.submitPaymentSearchRequest(customerNumber,from,to,action);
		
		if(response != null){
			redirectAttributess.addFlashAttribute("customerSearchPayment", response);
			
		}else{
			redirectAttributess.addFlashAttribute(PlatformFields.PAYMENT_SEARCH_MESSAGE, "No Payment search results was returned for Customer Number: "+customerNumber+" Date from:"+from+" to: "+to);
		}
		
		action = null;
		from = null;
		to = null;
		customerNumber = null;
		return  redirect;
	}
}
