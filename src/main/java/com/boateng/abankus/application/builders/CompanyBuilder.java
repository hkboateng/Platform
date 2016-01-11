/**
 * hkboateng
 */
package com.boateng.abankus.application.builders;

import javax.servlet.http.HttpServletRequest;

import com.boateng.abankus.domain.Address;
import com.boateng.abankus.domain.Company;
import com.boateng.abankus.domain.ContactPerson;
import com.boateng.abankus.domain.Email;
import com.boateng.abankus.domain.Phone;

/**
 * @author hkboateng
 *
 */
public class CompanyBuilder {

	private String firstname = null;
	
	private String lastname = null;
	
	private String address1 = null;
	
	private String address2 = null;
	
	private String emailAddress = null;
	
	private String phoneNumber = null;
	
	private String phoneType = null;
	
	private String companyName = null;
	
	private String state = null;
	
	private String city = null;
	
	private String zipcode = null;
	
	private Company company;
	
	private Email email;
	
	private Phone phone;
	
	private Address address;
	
	private ContactPerson contactPerson;
	
	public CompanyBuilder(){
		
	}
	
	public CompanyBuilder(HttpServletRequest request){
		firstname = request.getParameter("firstname");
		lastname = request.getParameter("lastname");
		address1 = request.getParameter("address");
		address2 = request.getParameter("address1");
		emailAddress = request.getParameter("emailAddress");
		phoneNumber = request.getParameter("phonenumber");
		phoneType = request.getParameter("phoneType");
		companyName = request.getParameter("companyname");
		state = request.getParameter("state");
		city = request.getParameter("city");
		zipcode= request.getParameter("zipcode");
	}
	
	public Company buildCompany(){
		company = new Company();
		company.setCompanyName(companyName);
		
		return company;
	}
	
	public ContactPerson addContactPersonDetails(){
		contactPerson = new ContactPerson();
		contactPerson.setFirstname(firstname);
		contactPerson.setLastname(lastname);
		contactPerson.setPhoneNumber(phoneNumber);
		contactPerson.setEmail(emailAddress);
		contactPerson.setAssociation("Contact");
		
		return contactPerson;
	}
	/**
	 * Creates a new Email Object
	 * 
	 * @param emailAddress
	 * @param emailType
	 * @return
	 */
	public Email addEmailDetails(){
		email = new Email();
		email.setEmailAddress(emailAddress);
		email.setEmailType("primary");
		
		return email;
	}
	
	public Phone addPhoneDetails(){
		phone = new Phone();
		phone.setPhoneNumber(phoneNumber);
		if(phoneType == null){
			phone.setPhoneType("work");
			phone.setPrimaryPhone(true);			
		}else{
			phone.setPhoneType(phoneType);
			phone.setPrimaryPhone(true);			
		}
		
		phone.setCountrycode("+1");		
		return phone;
	}
	
	public Address addAddressDetails(){
		address = new Address();
		address.setAddress1(address1);
		address.setAddress2(address2);
		address.setCity(city);
		address.setRegion(state);
		address.setZipcode(zipcode);
		address.setAddressType("primary");
		
		return address;
	}
	
	/**
	 * Sets all objects to Null so that garbage collector can clear them from memory.
	 */
	public void clearObject(){
		firstname = null;
		lastname = null;
		address1 = null;
		address2 = null;
		emailAddress = null;
		phoneNumber = null;
		phoneType = null;
		companyName = null;
		state = null;
		city = null;
		zipcode = null;		
	}
}
