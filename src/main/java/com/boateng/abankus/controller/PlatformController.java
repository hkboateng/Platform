/**
 * hkboateng
 */
package com.boateng.abankus.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import com.boateng.abankus.domain.PaymentTransaction;
import com.boateng.abankus.domain.Phone;
import com.boateng.abankus.exception.PlatformException;
import com.boateng.abankus.fields.CustomerOrderFields;
import com.boateng.abankus.fields.EmployeeFields;
import com.boateng.abankus.fields.PlatformFields;
import com.boateng.abankus.services.CustomerOrderService;
import com.boateng.abankus.services.EmployeeService;
import com.boateng.abankus.services.PaymentService;
import com.boateng.abankus.servlet.PlatformAbstractServlet;
import com.boateng.abankus.utils.PlatformConverter;
import com.boateng.abankus.utils.SecurityUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author hkboateng
 *
 */

@Controller
public class PlatformController  extends PlatformAbstractServlet {
	
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
	
	private static final Logger logger = Logger.getLogger(PlatformController.class.getName());

	
	@RequestMapping(value = "/platform/index", method = RequestMethod.GET)
	public String home(HttpServletRequest request, Model model) {
		try {
			loadUserIntoSession(request);
			loadEmployeeIntoSessionByUsername(request);
		} catch (Exception e) {
			PlatformException ace  = new PlatformException();
			ace.logger(Level.WARNING,e.getMessage(), e);

		}
		return "dashboard/dashboard";
	}
	@RequestMapping(value = "/platform/settings", method = RequestMethod.GET)
	public String settings() {

		return "dashboard/Settings";
	}	
	@RequestMapping(value = "/platform/dashboard", method = RequestMethod.GET)
	public String dashbaord(Locale locale, Model model,HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
				
		try {
				
			loadUserIntoSession(request);
			loadEmployeeIntoSessionByUsername(request);
			Employee employee = (Employee) session.getAttribute(EmployeeFields.EMPLOYEE_SESSION);
			int employeeId = employee.getId();	
			loadProductIntoSession(request);
			employee = null;
			List<Customer> customerList = employeeServiceImpl.findAllCustomerByEmployeeId(employeeId);
			model.addAttribute(EmployeeFields.EMPLOYEE_CUSTOMER_LIST, customerList);
			customerList = null;
		} catch (Exception e) {
			PlatformException ace  = new PlatformException();
			ace.logger(Level.WARNING,e.getMessage(), e);

		}
		
		return "dashboard/dashboard";
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

	
	@RequestMapping(value = "/platform/searchDashboard", method = RequestMethod.POST)
	public String searchDashBoard(HttpServletRequest request,Model model,RedirectAttributes redirectAttributess){
		String customerId = request.getParameter("customerId");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String orderNumber = request.getParameter("orderNumber");
		String searchType=request.getParameter("searchType");
		HttpSession session = request.getSession(false);
		
		Customer customer = null;
		if(searchType.equals("customerId")){
			customer = customerServiceProcessor.searchForCustomer(customerId);
		}else if(searchType.equals("customerName")){
			customer = customerServiceProcessor.searchForCustomerByFirstAndLastName(firstname, lastname);
		}else if(searchType.equals("order")){
			//search by order number, confirmation number or transaction Id
		}
		if(customer == null){
			session.setAttribute("searchError", "No information was found, Try again.");
			return "redirect:/platform/dashboard";
		}

				
		loadCustomerIntoSession(request,customer);
		CustomerAccount customerAccount = customerServiceProcessor.findCustomerAccountByCustomerNumber(customer.getCustomerId());
		
		List<Address> address = customerServiceProcessor.findAddressByCustomerId(customer.getCustomerId());
		
		List<Phone> phone = customerServiceProcessor.findCustomerPhoneByCustomerId(customer.getCustomerId());
		
		List<Email> email = customerServiceProcessor.findCustomerEmailByCustomerId(customer.getCustomerId());
		model.addAttribute("address",address);
		model.addAttribute("customerAccount",customerAccount);
		model.addAttribute("customer",customer);
		model.addAttribute("phone",phone);
		model.addAttribute("email",email);
		
		return "ClientServices/ViewCustomerProfile";
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
		
		PaymentTransaction payment = paymentServiceImpl.findPaymentTransactionByTransactionId(customerNumber);
		model.addAttribute("transaction", payment);
		return "ClientTransaction/TransactionHistory";
	}	
	
	@RequestMapping(value = "/platform/viewTransactionDetail", method = RequestMethod.POST)
	public String transactionDetails(HttpServletRequest request,Model model) throws PlatformException{
		String orderNumber = request.getParameter("orderNumber");
		HttpSession session = request.getSession(false);
		
		orderNumber = SecurityUtils.decryptOrderNumber(orderNumber);
		BillingCollection collection = (BillingCollection) session.getAttribute(CustomerOrderFields.BILLING_COLLECTION_SESSION);
		
		List<PaymentTransaction> payment = null;
		CustomerBilling billing = null;
		List<OrderPayment> orderPayment = null;
		if(collection != null){
			billing = collection.getCustomerBilling(orderNumber);
			orderPayment = collection.getCustomerBillingPayment(orderNumber);
				
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
		for(OrderPayment list: orderPayment){
			transaction = new PaymentTransaction(list);
			payments.add(transaction);
		}
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
	
}
