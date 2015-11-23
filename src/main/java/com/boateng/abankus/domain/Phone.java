package com.boateng.abankus.domain;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

import java.math.BigInteger;


/**
 * The persistent class for the phone database table.
 * 
 */
@Entity
@DynamicUpdate(value=true)
@NamedQuery(name="Phone.findAll", query="SELECT p FROM Phone p")
public class Phone implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int phoneId;

	private String phoneNumber;

	private String countrycode;

	private String phoneType;

	private boolean primaryPhone;
	
	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="customerId")
	private Customer customer;

	public Phone() {
	}

	public int getPhoneId() {
		return this.phoneId;
	}

	public void setPhoneId(int phoneId) {
		this.phoneId = phoneId;
	}

	
	/**
	 * @return the countrycode
	 */
	public String getCountrycode() {
		return countrycode;
	}

	/**
	 * @param countrycode the countrycode to set
	 */
	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	public String getPhoneType() {
		return this.phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String phoneType(){
		StringBuilder sbr = new StringBuilder();
		if(getPhoneType() != null && getPhoneType().equals("home_phone")){
			sbr.append("Home Phone");
		}else if(getPhoneType() != null && getPhoneType().equals("cell_phone")){
			sbr.append("Cell/Mobile Phone");
		} else {
			sbr.append("Other Phone");
		}
		return sbr.toString();
	}

	public boolean isPrimaryPhone() {
		return primaryPhone;
	}

	public void setPrimaryPhone(boolean primaryPhone) {
		this.primaryPhone = primaryPhone;
	}
}