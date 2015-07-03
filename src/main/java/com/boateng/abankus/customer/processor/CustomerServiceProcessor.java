package com.boateng.abankus.customer.processor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.boateng.abankus.domain.Address;
import com.boateng.abankus.domain.Customer;
import com.boateng.abankus.domain.Email;
import com.boateng.abankus.domain.Gender;
import com.boateng.abankus.domain.Phone;
import com.boateng.abankus.employee.interfaces.CustomerService;
import com.boateng.abankus.fields.AddressFields;
import com.boateng.abankus.fields.CustomerFields;
import com.boateng.abankus.fields.EmailFields;
import com.boateng.abankus.fields.PhoneFields;

@Service
public class CustomerServiceProcessor {


	@Autowired(required=true)
	@Qualifier(value="customerServiceImpl")
	private CustomerService customerServiceImpl;
	
	
	public Email addEmail(String emailAddress,String emailType){
		Email email = new Email(emailAddress);
		email.setEmailType(emailType);
		if(email.getEmailType() == null || email.getEmailType().isEmpty()){
			email.setEmailType(EmailFields.PRIMARY_EMAIL);
		}
		return email;
	}
	
	public Phone addCustomerPhone(String countrycode, String areacode,String middlenumber,String exchange,String extension, String phoneType){
		Phone phone = new Phone();
		phone.setCountrycode(countrycode);
		if(countrycode == null || countrycode.isEmpty()){
			phone.setCountrycode(PhoneFields.DEFAULT_COUNTRY_CODE);
		}
		
		phone.setAreacode(areacode);
		phone.setMiddle(middlenumber);
		phone.setExchange(exchange);
		//phone.setExtension(extension);
		if(phone.getPhoneType() == null || phone.getPhoneType().isEmpty()){
			phone.setPhoneType(PhoneFields.HOME_PHONE);
		}
		return phone;
	}
	
	public Address addCustomerAddress(String address1,String address2, String city, String state, String zipcode,String country,String addressType){
		Address address = new Address(address1,addressType,city,state,zipcode);
		
		address.setAddress2(address2);
		/**
		address.setCountry(country);
		
		if(country== null || country.isEmpty()){
			address.setCountry(AddressFields.DEFAULT_COUNTRY);
		}
		**/
		return address;
	}
	
	public Customer addIndividualCustomer(String firstname,String middlename,String lastname,String companyName, String customerType){
		Customer customer = new Customer(companyName,customerType);
		customer.setFirstname(firstname);
		customer.setMiddlename(middlename);
		customer.setLastname(lastname);
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
		String areacode = request.getParameter(PhoneFields.AREA_CODE_LABEL);
		String middlenumber = request.getParameter(PhoneFields.MIDDLE_NUMBER_LABEL);
		String exchange = request.getParameter(PhoneFields.EXCHANGE_LABEL);
		String extension = request.getParameter(PhoneFields.EXTENSION_LABEL);
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
		SimpleDateFormat formatter = new SimpleDateFormat("MMMM/dd/yyyy");
		
		//Date dateOfBirth = formatter.parse(dataOfBirth);
		//Gender sex = Gender.valueOf(gender);
		/**
		SimpleDateFormat formatter = new SimpleDateFormat("MMMM/dd/yyyy");
		StringBuilder str = new StringBuilder();
		str.append(month).append("/").append(day).append("/").append(year);
		
		Date dateOfBirth = formatter.parse(str.toString());
		**/
		/** Creating new Email Object***/
		Email email = addEmail(emailAddress,emailType);
		
		/** Creating new Phone Object**/
		Phone phone = addCustomerPhone(countrycode, areacode,middlenumber,exchange,extension,phoneType);
		
		/**Creating new Address Object**/
		Address address = addCustomerAddress(address1,address2,city,state,zipcode,country,addressType);
		
		/** Creating Customer Object**/
		Customer customers = addIndividualCustomer(firstname,middlename,lastname,companyname,customerType);

		customers =  customerServiceImpl.addNewCustomer(customers,email,phone,address);

	}

	
}
