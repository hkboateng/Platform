/**
 * hkboateng
 */
package com.boateng.abankus.processors;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.boateng.abankus.domain.Address;
import com.boateng.abankus.domain.Company;
import com.boateng.abankus.domain.ContactPerson;
import com.boateng.abankus.domain.Email;
import com.boateng.abankus.domain.Phone;
import com.boateng.abankus.services.CompanyService;
import com.boateng.abankus.utils.SecurityUtils;

/**
 * @author hkboateng
 *
 */
public class CompanyProcessor {

	private static final Logger logger = Logger.getLogger(CompanyProcessor.class.getName());
	
	@Autowired
	private CompanyService companyServiceImpl;
	
	public List<String> validateCompany(HttpServletRequest request){
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String address1 = request.getParameter("address");
		String address2 = request.getParameter("address2");
		String emailAddress = request.getParameter("emailAddress");
		String phone = request.getParameter("phone");
		String phoneType = request.getParameter("phoneType");
		String companyName = request.getParameter("companyName");
		String password = request.getParameter("password");
		String taxId = request.getParameter("taxId");
		String state = request.getParameter("state");
		String city = request.getParameter(" city");
		String zipcode= request.getParameter("zipcode");
		
		ArrayList<String> validation = null;
		validation = new ArrayList<String>();
		if(StringUtils.isBlank(firstname) && StringUtils.isEmpty(firstname)){
			validation.add("First Name fields cannot be null.");
		}
		firstname = null;
		lastname = null;
		address1 = null;
		address2 = null;
		emailAddress = null;
		phone = null;
		phoneType = null;
		companyName = null;
		password = null;
		taxId = null;
		state = null;
		city = null;
		zipcode= null;	
		
		return validation;
	}

	/**
	 * @param request
	 */
	public Company buildAndSubmitCompany(HttpServletRequest request){

		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String address1 = request.getParameter("address");
		String address2 = request.getParameter("address1");
		String emailAddress = request.getParameter("emailAddress");
		String phoneNumber = request.getParameter("phonenumber");
		String phoneType = request.getParameter("phoneType");
		String companyName = request.getParameter("companyname");
		//String dba = request.getParameter("dba");
		//String taxId = request.getParameter("taxId");
		String state = request.getParameter("state");
		String city = request.getParameter("city");
		String zipcode= request.getParameter("zipcode");
		
		Company company = new Company();
		//company.setBusinessname(dba);
		company.setCompanyName(companyName);
		//company.setTaxId(taxId);
		//company.setBusinessType("retail");
		
		ContactPerson contactPerson = new ContactPerson();
		contactPerson.setFirstname(firstname);
		contactPerson.setLastname(lastname);
		contactPerson.setPhoneNumber(phoneNumber);
		contactPerson.setEmail(emailAddress);
		contactPerson.setAssociation("Contact");
		
		Phone phone = new Phone();
		phone.setPhoneNumber(phoneNumber);
		if(phoneType == null){
			phone.setPhoneType("work");
			phone.setPrimaryPhone(true);			
		}else{
			phone.setPhoneType(phoneType);
			phone.setPrimaryPhone(true);			
		}
		
		phone.setCountrycode("+1");
		
		Email email = new Email();
		email.setEmailAddress(emailAddress);
		email.setEmailType("primary");
		
		Address address = new Address();
		address.setAddress1(address1);
		address.setAddress2(address2);
		address.setCity(city);
		address.setRegion(state);
		address.setZipcode(zipcode);
		address.setAddressType("primary");
		
		
		//company = submitCompany(company,email,phone,address,contactPerson);
		return company;
	}
	
	public Company submitCompany(Company company,Email email, Phone phone, Address address, ContactPerson person){
		/**
		 * company.setEmailId(email);
		company.setPhoneId(phone);
		company.setAddressId(address);
		company.setContactId(contactPerson);
		 */
		Integer companyId = SecurityUtils.generateCompanyNumber();
		company.setCompanyNumber(companyId);
		company = companyServiceImpl.saveCompany(company, email, phone, address, person);
		
		return company;
	}
}
