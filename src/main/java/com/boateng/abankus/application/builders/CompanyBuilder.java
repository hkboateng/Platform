/**
 * hkboateng
 */
package com.boateng.abankus.application.builders;

import javax.servlet.http.HttpServletRequest;

import com.boateng.abankus.application.ws.svc.AuthenticationEmployeeRequest;
import com.boateng.abankus.domain.Address;
import com.boateng.abankus.domain.Company;
import com.boateng.abankus.domain.ContactPerson;
import com.boateng.abankus.domain.Email;
import com.boateng.abankus.domain.Employee;
import com.boateng.abankus.domain.Phone;

/**
 * @author hkboateng
 *
 */
public class CompanyBuilder extends AbstractBuilder{

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
		company.setAddressBean(addAddressDetails());
		company.setEmailBean(addEmailDetails());
		company.setPhoneBean(addPhoneDetails());
		company.setContactperson(addContactPersonDetails());
		return company;
	}
	
	public ContactPerson addContactPersonDetails(){
		contactPerson =  addContactPersonDetails(firstname,lastname,phoneNumber,emailAddress);
		
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
		email = addEmailDetails(emailAddress);
		
		return email;
	}
	
	public Phone addPhoneDetails(){
		
		phone = addPhoneDetails(phoneNumber,phoneType);		
		return phone;
	}
	
	public Address addAddressDetails(){
		address = addAddressDetails(address1,address2,city,state,zipcode);
		
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
	public AuthenticationEmployeeRequest getEmployeeInformation(){
		AuthenticationEmployeeRequest request = new AuthenticationEmployeeRequest();
		request.setAddress1(address1);
		request.setAddress2(address2);
		request.setCity(city);
		request.setEmailAddress(emailAddress);
		request.setEnabled(true);
		request.setFirstname(firstname);
		request.setPhoneNumber(phoneNumber);
		request.setState(state);
		request.setZipcode(zipcode);
		return request;
	}
}
