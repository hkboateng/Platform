package com.boateng.abankus.employees.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.boateng.abankus.fields.EmployeeFields;

public class EmployeeValidation {

	private static EmployeeValidation employeeValidation = new EmployeeValidation();
	private Pattern pattern = null;
	private Matcher matcher = null;
	private EmployeeValidation(){
		
	}
	
	public static EmployeeValidation getInstance(){
		return employeeValidation;
	}
	public List<String> validPersonalInformation(HttpServletRequest request){
		List<String> errors = new ArrayList<String>();
		String firstname = request.getParameter("firstname").trim();
		String lastname = request.getParameter("lastname").trim();
		String address1= request.getParameter("address1").trim();
		String city= request.getParameter("city").trim();
		String state= request.getParameter("state").trim();
		String zipcode= request.getParameter("zip").trim();
		String month= request.getParameter("month").trim();
		String day= request.getParameter("day").trim();
		String year= request.getParameter("year").trim();
		String gender= request.getParameter("gender").trim();
		String email = request.getParameter("email").trim();
		String cellPhone = request.getParameter("cellPhone").trim();	
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if(username == null || username.isEmpty()){
			errors.add("Username or Password cannot be null.");
		}
		if(password == null || password.isEmpty()){
			errors.add("Username or Password cannot be null.");
		}		
		if(firstname == null || firstname.isEmpty()){
			errors.add("First Name value cannot be null");
		}
		if(lastname == null || lastname.isEmpty()){
			errors.add("Last Name value cannot be null");
		}
		if(address1 == null || address1.isEmpty()){
			errors.add("Address 1 field cannot be null");
		}
		if(city == null || city.isEmpty()){
			errors.add("City field cannot be null");
		}
		if(state == null || state.isEmpty()){
			errors.add("State field cannot be null");
		}
		
		if(zipcode == null ||   !isZipCodeValid(zipcode)){
			errors.add("Zip Code field cannot be null or less than 5 characters");
		}
		if(month == null || month.isEmpty()){
			errors.add("Month field cannot be null");
		}
		if(day == null || day.isEmpty()){
			errors.add("Day field cannot be null");
		}
		if(gender == null || gender.isEmpty()){
			errors.add("Gender field cannot be null");
		}
		if(year == null || year.isEmpty()){
			errors.add("Year field cannot be null");
		}			
		if(!isEmailValid(email)){
			errors.add("Email Address is invalid");
		}
		if(email == null){
			errors.add("Email Address cannot be null.");
		} 
		if(cellPhone == null){
			errors.add("Cell Phone Number cannot be null.");
		}
		return errors;
	}
	
	public List<String> validateDepartmentInfo(HttpServletRequest reques){
		List<String> errors = new ArrayList<String>();
		
		return errors;
	}
	
	public boolean isZipCodeValid(String zip){
		
		pattern = Pattern.compile(EmployeeFields.ZIPCODE_REGEX);
		matcher = pattern.matcher(zip);
		
		return matcher.matches();
	}
	
	public boolean isEmailValid(String email){
		pattern = Pattern.compile(EmployeeFields.EMAIL_PATTERN);
		matcher = pattern.matcher(email);		
		
		return matcher.matches();
	}


}
