/**
 * hkboateng
 */
package com.boateng.abankus.application.builders;

import javax.servlet.http.HttpServletRequest;

import com.boateng.abankus.domain.ContactPerson;

/**
 * @author hkboateng
 *
 */
public class ContactPersonBuilder {
	
	private String firstname;
	
	String lastname;
	
	String email;
	
	String phonenumber;
	
	private String association;
	
	ContactPerson person = null;
	
	public ContactPersonBuilder(HttpServletRequest request){
		this.firstname = request.getParameter("firstname");
		this.lastname = request.getParameter("lastname");
		this.email = request.getParameter("emailAddress");
		this.phonenumber = request.getParameter("phonenumber");
		this.association = request.getParameter("association");
	}
	
	public ContactPerson buildContactPerson(){
		person = new ContactPerson();
		if(association != null){
			person.setAssociation(association);
		}
		person.setEmail(email);
		person.setFirstname(firstname);
		person.setLastname(lastname);
		person.setPhoneNumber(phonenumber);
		
		return person;
	}
}
