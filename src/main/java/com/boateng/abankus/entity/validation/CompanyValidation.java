/**
 * hkboateng
 */
package com.boateng.abankus.entity.validation;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.boateng.abankus.utils.ValidationUtils;

/**
 * @author hkboateng
 *
 */
public class CompanyValidation {

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
	/**
	 * 
	 */
	public CompanyValidation() {
		// Default Constructor
	}
	
	public CompanyValidation(HttpServletRequest request) {
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
	
	public List<String> validateCompany(){
		List<String> validation = new LinkedList<String>();
		if(!ValidationUtils.isAlpha(firstname)){
			validation.add("First Name contains invalid charaters.");
		}
		if(!ValidationUtils.isEmailValid(emailAddress)){
			validation.add("Email Address is not valid. Enter a valid Email Address");
		}
		
		if(!ValidationUtils.isZipCodeValid(zipcode)){
			validation.add("Zip Code is not valid. Enter a valid Zip Code.");
		}
		return validation;
	}
	

}
