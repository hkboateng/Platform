/**
 * hkboateng
 */
package com.boateng.abankus.application.builders;

import com.boateng.abankus.domain.Address;
import com.boateng.abankus.domain.ContactPerson;
import com.boateng.abankus.domain.Email;
import com.boateng.abankus.domain.Phone;

/**
 * @author hkboateng
 *
 */
public abstract class AbstractBuilder {

	public ContactPerson addContactPersonDetails(String firstname, String lastname, String phoneNumber, String emailAddress){
		ContactPerson contactPerson = new ContactPerson();
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
	public Email addEmailDetails(String emailAddress){
		Email email = new Email();
		email.setEmailAddress(emailAddress);
		email.setEmailType("primary");
		
		return email;
	}
	
	public Phone addPhoneDetails(String phoneNumber, String phoneType){
		Phone phone = new Phone();
		phone.setPhoneNumber(phoneNumber);
		if(phoneType == null){
			phone.setPhoneType("work");
			phone.setPrimaryPhone(true);			
		}else{
			phone.setPhoneType(phoneType);
			phone.setPrimaryPhone(true);			
		}
		
		phone.setCountrycode("+233");		
		return phone;
	}
	
	public Address addAddressDetails(String address1,String address2,String city, String state, String zipcode){
		Address address = new Address();
		address.setAddress1(address1);
		address.setAddress2(address2);
		address.setCity(city);
		address.setRegion(state);
		address.setZipcode(zipcode);
		address.setAddressType("primary");
		
		return address;
	}
}
