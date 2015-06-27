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
	
	
	//private String
}
