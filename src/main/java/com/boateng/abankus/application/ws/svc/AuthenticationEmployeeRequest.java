package com.boateng.abankus.application.ws.svc;

import com.boateng.abankus.domain.Employee;

public class AuthenticationEmployeeRequest {

	private String username;
	
	private String hashpassword;
	
	private String[] roles;

	private boolean enabled;
	
	
	private String emailAddress;
	
	private Long employeeId;
	
	private long companyId;
	
	private String address1;

	private String address2;
	

	private String phoneNumber;
	

	private String city;

	private String firstname;


	private String gender;

	private String homephone;


	private String lastname;

	private String middlename;


	private String state;


	private String zipcode;

	
	private  long companyNumber;

	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the hashpassword
	 */
	public String getHashpassword() {
		return hashpassword;
	}

	/**
	 * @param hashpassword the hashpassword to set
	 */
	public void setHashpassword(String hashpassword) {
		this.hashpassword = hashpassword;
	}

	/**
	 * @return the roles
	 */
	public String[] getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	/**
	 * @param username
	 * @param hashpassword
	 * @param roles
	 */
	public AuthenticationEmployeeRequest(String username, String hashpassword, String[] roles) {
		this.username = username;
		this.hashpassword = hashpassword;
		this.roles = roles;
	}

	/**
	 * 
	 */
	public AuthenticationEmployeeRequest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return the employeeId
	 */
	public Long getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the companyId
	 */
	public long getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @param address1 the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @param address2 the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}



	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the homephone
	 */
	public String getHomephone() {
		return homephone;
	}

	/**
	 * @param homephone the homephone to set
	 */
	public void setHomephone(String homephone) {
		this.homephone = homephone;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the middlename
	 */
	public String getMiddlename() {
		return middlename;
	}

	/**
	 * @param middlename the middlename to set
	 */
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * @return the companyNumber
	 */
	public  long getCompanyNumber() {
		return companyNumber;
	}

	/**
	 * @param companyNumber the companyNumber to set
	 */
	public void setCompanyNumber( long companyNumber) {
		this.companyNumber = companyNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
}
