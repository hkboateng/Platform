package com.boateng.abankus.customer.processor;

import java.text.ParseException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.boateng.abankus.domain.Address;
import com.boateng.abankus.domain.Customer;
import com.boateng.abankus.domain.CustomerAccount;
import com.boateng.abankus.domain.Email;
import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.Phone;
import com.boateng.abankus.employee.interfaces.AuthenticationService;
import com.boateng.abankus.employee.interfaces.CustomerService;
import com.boateng.abankus.employee.interfaces.EmployeeService;
import com.boateng.abankus.fields.AddressFields;
import com.boateng.abankus.fields.CustomerFields;
import com.boateng.abankus.fields.EmailFields;
import com.boateng.abankus.fields.PhoneFields;
import com.boateng.abankus.utils.SecurityUtils;

@Service
public class CustomerServiceProcessor {


	/**
	 * 
	 */
	
	@Autowired(required=true)
	@Qualifier(value="customerServiceImpl")
	private CustomerService customerServiceImpl;

	@Autowired(required=true)
	@Qualifier(value="employeeSvcImpl")
	private EmployeeService employeeSvcImpl;

	@Autowired(required=true)
	@Qualifier(value="authenticationServiceImpl")
	private AuthenticationService authenticationServiceImpl;
	
	ExecutorService executorService = Executors.newFixedThreadPool(10);
	
	public Email addEmail(String emailAddress,String emailType){
		Email email = new Email(emailAddress);
		email.setEmailType(emailType);
		if(email.getEmailType() == null || email.getEmailType().isEmpty()){
			email.setEmailType(EmailFields.PRIMARY_EMAIL);
		}
		return email;
	}
	
	public Phone addCustomerPhone(String countrycode, String phoneNumber, String phoneType){
		Phone phone = new Phone();
		phone.setCountrycode(countrycode);
		if(countrycode == null || countrycode.isEmpty()){
			phone.setCountrycode(PhoneFields.DEFAULT_COUNTRY_CODE);
		}

		phone.setPhoneNumber(phoneNumber);
		if(phone.getPhoneType() == null || phone.getPhoneType().isEmpty()){
			phone.setPhoneType(PhoneFields.HOME_PHONE);
		}
		return phone;
	}
	
	public Address addCustomerAddress(String address1,String address2, String city, String state, String zipcode,String country,String addressType){
		Address address = new Address(address1,addressType,city,state,zipcode);
		
		address.setAddress2(address2);
		address.setAddressType("primary");

		return address;
	}
	
	public Customer addIndividualCustomer(String firstname,String middlename,String lastname,String companyName, String customerType, String gender){
		Customer customer = new Customer(firstname,lastname,customerType);
		String customerNo = SecurityUtils.generateCustomerId();
		if(customerServiceImpl.findCustomerByCustomerNumber(customerNo) == null){
			customerNo = SecurityUtils.generateCustomerId();
		}
		customer.setCustomerNumber(customerNo);
		customer.setCompany_name(companyName);
		customer.setMiddlename(middlename);
		customer.setGender(gender);
		if(customerType.equalsIgnoreCase(CustomerFields.CUSTOMER_INDIVIDUAL)){
			customer.setContactPerson(CustomerFields.CONTACT_PERSON_NO);
		}
		if(customerType.equalsIgnoreCase(CustomerFields.CUSTOMER_COMPANY) && (firstname != null && lastname != null)){
			customer.setContactPerson(CustomerFields.CONTACT_PERSON_YES);
		}		
		return customer;
	}
	
	
	/**
	 * This method is used to create all the objects necessary to save
	 * a new Customer
	 * @param request
	 * @throws ParseException 
	 */
	
	public void processNewCustomer(HttpServletRequest request) throws ParseException{
		String emailAddress = request.getParameter(EmailFields.EMAIL_ADDRESS_LABEL);
		String emailType = request.getParameter(EmailFields.EMAIL_TYPE_LABEL);
		
		String countrycode = request.getParameter(PhoneFields.COUNTRY_CODE_LABEL);
		String phoneNumber = request.getParameter(PhoneFields.PHONE_NUMBER_LABEL);
		String phoneType = request.getParameter(PhoneFields.PHONE_TYPE_LABEL);
		
		String address1 = request.getParameter(AddressFields.ADDRESS1_LABEL);
		String address2 = request.getParameter(AddressFields.ADDRESS2_LABEL);
		String city = request.getParameter(AddressFields.CITY_LABEL);
		String state = request.getParameter(AddressFields.STATE_LABEL);
		String zipcode = request.getParameter(AddressFields.ZIPCODE_LABEL);
		String country = request.getParameter(AddressFields.ADDRESS_COUNTRY_LABEL);
		String addressType = request.getParameter(AddressFields.ADDRESS_TYPE_LABEL);
		
		String firstname = request.getParameter(CustomerFields.FIRST_NAME_LABEL);
		String lastname = request.getParameter(CustomerFields.LAST_NAME_LABEL);
		String middlename = request.getParameter(CustomerFields.MIDDLE_NAME_LABEL);
		String companyname = request.getParameter(CustomerFields.COMPANY_NAME);
		String customerType = request.getParameter(CustomerFields.CUSTOMER_TYPE);
		String gender = request.getParameter(CustomerFields.GENDER_LABEL);
		String dataOfBirth = request.getParameter(CustomerFields.DATE_OF_BIRTH_LABEL);


		//Gender sex = Gender.valueOf(gender);

		/** Creating new Email Object***/
		Email email = addEmail(emailAddress,emailType);
		
		/** Creating new Phone Object**/
		Phone phone = addCustomerPhone(countrycode, phoneNumber,phoneType);
		
		/**Creating new Address Object**/
		Address address = addCustomerAddress(address1,address2,city,state,zipcode,country,addressType);
		
		/** Creating Customer Object**/
		Customer customers = addIndividualCustomer(firstname,middlename,lastname,companyname,customerType,gender);
		
		processNewCustomer(customers,email,phone,address,request);
		
		
	}

	public void processNewCustomer(Customer customers, Email email, Phone phone, Address address,HttpServletRequest request){
		String username = request.getUserPrincipal().getName();
		Employee employee =authenticationServiceImpl.findEmployeeByUserName(username);

		Customer customer =  customerServiceImpl.addNewCustomer(customers,email,phone,address);
		//EmployeeServiceProcessor process = new EmployeeServiceProcessor();
		String industry = request.getParameter("industry");
		String notes = request.getParameter("notes");
		employeeSvcImpl.addEmployeeSalesAccount(employee, customer,industry,notes);
	}
	
	public Set<Customer> getAllCustomers(){
		Set<Customer> customers = null;
		List<Customer> customerList = customerServiceImpl.getAllCustomers();
		if(customerList == null || customerList.isEmpty() ){
			return null;
		}
		customers = new LinkedHashSet<Customer>(customerList);

		return customers;
	}
	
	public Customer findCustomerByCustomerId(int Id){
		Customer customer = customerServiceImpl.findCustomerByCustomerId(Id);
		
		return customer;
	}
	
	public CustomerAccount findCustomerAccountByCustomerNumber(String customerNo){
		CustomerAccount customerAccount = customerServiceImpl.findCustomerAccountByCustomerNumber(customerNo);
		return customerAccount;
	}
	
	public List<Address> findAddressByCustomerId(int Id){
		List<Address> address = customerServiceImpl.findAddressByCustomerId(Id);
		return address;
	}
}
