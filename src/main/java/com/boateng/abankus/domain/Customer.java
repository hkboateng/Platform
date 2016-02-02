package com.boateng.abankus.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;


/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@DynamicUpdate(value=true)
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int customerId;

	@Column(name="company_name")
	private String companyName;

	private String customerNumber;

	private long companyId;
	
	private String firstname;

	private String gender;

	private String lastname;

	private String middlename;

	@OneToOne
	@JoinColumn(name="addressId")
	private Address addressId;
	
	@OneToOne
	@JoinColumn(name="phoneId")
	private Phone phoneId;
	
	@OneToOne
	@JoinColumn(name="emailId")
	private Email emailId;
	
	public Customer() {
	}

	/**
	 * @param companyName
	 * @param companyNumber
	 */
	public Customer(String firstname,String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
		
	}

	
	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public Address getAddressId() {
		return this.addressId;
	}

	public void setAddressId(Address addressId) {
		this.addressId = addressId;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getCustomerNumber() {
		return this.customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}


	public Email getEmailId() {
		return this.emailId;
	}

	public void setEmailId(Email emailId) {
		this.emailId = emailId;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMiddlename() {
		return this.middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public Phone getPhoneId() {
		return this.phoneId;
	}

	public void setPhoneId(Phone phoneId) {
		this.phoneId = phoneId;
	}

	public String getCustomerName(){
		StringBuilder sbr = new StringBuilder();
		if(getFirstname() !=null && getLastname() !=null){
			sbr.append(getFirstname()).append(" ");
			if(getMiddlename() != null){
				sbr.append(getMiddlename()).append(" ");
			}
			sbr.append(getLastname());
		}else{
			sbr.append(getCompanyName());
		}
		return sbr.toString();
	}

	public String getContactInformation(){
		StringBuilder sbr = new StringBuilder();
		if(getAddressId() != null){
			sbr.append(getAddressId().getAddress1()).append("\n").append(getAddressId().getAddress2());
		}
		return sbr.toString();
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
}