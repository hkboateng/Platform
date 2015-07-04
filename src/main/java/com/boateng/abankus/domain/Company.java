package com.boateng.abankus.domain;

import java.io.Serializable;
import java.net.URI;

import javax.persistence.Id;
import javax.validation.constraints.Null;

/**
 * 
 * @author hkboateng
 * Company class is the customer
 * 
 */
public class Company implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5329208303763227646L;
	
	@Id
	private int id;
	
	private Long compnayId;
	
	private String company_name;
	
	@Null
	private String company_name1;
	
	private String address;
	
	@Null
	private String address1;
	
	@Null
	private String address2;

	private String city;
	
	private String state;
	
	private String zipcode;
	
	private String email;
	
	@Null
	private URI webAddress;
	
	private String phone1;
	
	@Null
	private String phone2;
	
	@Null
	private String phone3;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the compnayId
	 */
	public Long getCompnayId() {
		return compnayId;
	}

	/**
	 * @param compnayId the compnayId to set
	 */
	public void setCompnayId(Long compnayId) {
		this.compnayId = compnayId;
	}

	/**
	 * @return the company_name
	 */
	public String getCompany_name() {
		return company_name;
	}

	/**
	 * @param company_name the company_name to set
	 */
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	/**
	 * @return the company_name1
	 */
	public String getCompany_name1() {
		return company_name1;
	}

	/**
	 * @param company_name1 the company_name1 to set
	 */
	public void setCompany_name1(String company_name1) {
		this.company_name1 = company_name1;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the webAddress
	 */
	public URI getWebAddress() {
		return webAddress;
	}

	/**
	 * @param webAddress the webAddress to set
	 */
	public void setWebAddress(URI webAddress) {
		this.webAddress = webAddress;
	}

	/**
	 * @return the phone1
	 */
	public String getPhone1() {
		return phone1;
	}

	/**
	 * @param phone1 the phone1 to set
	 */
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	/**
	 * @return the phone2
	 */
	public String getPhone2() {
		return phone2;
	}

	/**
	 * @param phone2 the phone2 to set
	 */
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	/**
	 * @return the phone3
	 */
	public String getPhone3() {
		return phone3;
	}

	/**
	 * @param phone3 the phone3 to set
	 */
	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}
	
	
	//private String
	
}
