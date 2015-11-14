/**
 * hkboateng
 */
package com.boateng.abankus.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boateng.abankus.customer.processor.CustomerServiceProcessor;
import com.boateng.abankus.domain.Address;
import com.boateng.abankus.domain.Customer;
import com.boateng.abankus.domain.CustomerAccount;
import com.boateng.abankus.domain.CustomerTransaction;
import com.boateng.abankus.domain.Email;
import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.OrderPayment;
import com.boateng.abankus.domain.PaymentTransaction;
import com.boateng.abankus.domain.Phone;
import com.boateng.abankus.exception.PlatformException;
import com.boateng.abankus.fields.EmployeeFields;
import com.boateng.abankus.services.EmployeeService;
import com.boateng.abankus.services.PaymentService;
import com.boateng.abankus.servlet.PlatformAbstractServlet;
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
	@Qualifier(value="paymentServiceImpl")
	private PaymentService paymentServiceImpl;	
	
	@Autowired(required=true)
	private CustomerServiceProcessor customerServiceProcessor;
	
	private static final Logger logger = LoggerFactory.getLogger(PlatformController.class);
	
	@RequestMapping(value = "/platform/index", method = RequestMethod.GET)
	public String home(HttpServletRequest request, Model model) {
		
		
		return "dashboard/dashboard";
	}
	
	@RequestMapping(value = "/platform/dashboard", method = RequestMethod.GET)
	public String dashbaord(Locale locale, Model model,HttpServletRequest request) {
				
		try {
			loadUserIntoSession(request);
			loadEmployeeIntoSessionByUsername(request);
			loadProductIntoSession(request);
		} catch (Exception e) {
			PlatformException ace  = new PlatformException();
			ace.logger(Level.WARNING,e.getMessage(), e);

		}
		loadDashBoardInformation(request);
		return "dashboard/dashboard";
	}	
	
	private void loadDashBoardInformation(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		Employee employee = (Employee) session.getAttribute(EmployeeFields.EMPLOYEE_SESSION);
		int employeeId = employee.getId();
		List<Customer> customerList = employeeServiceImpl.findAllCustomerByEmployeeId(employeeId);
		session.setAttribute(EmployeeFields.EMPLOYEE_CUSTOMER_LIST, customerList);
		
		
		
		DateTime dateTime = new DateTime();
		LocalDate localDate = dateTime.toLocalDate();
		
		DateTime today = localDate.toDateTimeAtStartOfDay();
		SimpleDateFormat d = new SimpleDateFormat("MM/dd/yyyy");
		dateTime.equals(d);
	
		List<OrderPayment> payments = employeeServiceImpl.findAllPaymentByEmployeeAndDate(employeeId);
		if(payments !=null && !payments.isEmpty()){
			List<CustomerTransaction> transactionList = new ArrayList<CustomerTransaction>();
			CustomerTransaction transaction = null;
			for(OrderPayment p: payments){
				d.format(p.getPaymentDate());
				if(!today.isAfter(p.getPaymentDate().getTime())){
					transaction = new CustomerTransaction(p);
					transactionList.add(transaction);					
				}

			}
			session.setAttribute("transactionListToday", transactionList);
		}
		
		
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
			logger.error(e.getMessage());
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
			logger.error(e.getMessage());
		}
		return status;
	}
}
