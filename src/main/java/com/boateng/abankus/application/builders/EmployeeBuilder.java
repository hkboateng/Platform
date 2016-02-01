/**
 * hkboateng
 */
package com.boateng.abankus.application.builders;

import javax.servlet.http.HttpServletRequest;

import com.boateng.abankus.domain.Address;
import com.boateng.abankus.domain.ContactPerson;
import com.boateng.abankus.domain.Email;
import com.boateng.abankus.domain.Phone;

/**
 * @author hkboateng
 *
 */
public class EmployeeBuilder extends AbstractBuilder {

	private String firstname = null;
	
	private String lastname = null;
	
	private String address1 = null;
	
	private String address2 = null;
	
	private String emailAddress = null;
	
	private String phoneNumber = null;
	
	private String state = null;
	
	private String city = null;
	
	private String zipcode = null;
	
	private Email email;
	
	private Phone phone;
	
	private Address address;
	
	private ContactPerson contactPerson;
	
	public EmployeeBuilder(HttpServletRequest request){
		firstname = request.getParameter("firstname");
		lastname = request.getParameter("lastname");
		address1 = request.getParameter("address");
		address2 = request.getParameter("address1");
		emailAddress = request.getParameter("emailAddress");
		phoneNumber = request.getParameter("phonenumber");
		state = request.getParameter("state");
		city = request.getParameter("city");
		zipcode= request.getParameter("zipcode");
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
		String phoneType = "cellphone";
		phone = addPhoneDetails(phoneNumber,phoneType);		
		return phone;
	}
	
	public Address addAddressDetails(){
		address = addAddressDetails(address1,address2,city,state,zipcode);
		
		return address;
	}
}
