package com.boateng.abankus.domain;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

import java.math.BigInteger;


/**
 * The persistent class for the address database table.
 * 
 */
@Entity
@DynamicUpdate(value=true)
@NamedQuery(name="Address.findAll", query="SELECT a FROM Address a")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int addressId;

	private String address1;

	private String address2;

	private String addressType;

	private String city;

	private String region;

	private String zipcode;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="customerId")
	private Customer customer;

	public Address(){}
	/**
	 * @param address1
	 * @param addressType
	 * @param city
	 * @param region
	 * @param zipcode
	 */
	public Address(String address1, String addressType, String city,String region, String zipcode) {
		this.address1 = address1;
		this.addressType = addressType;
		this.city = city;
		this.region = region;
		this.zipcode = zipcode;
	}

	public int getAddressId() {
		return this.addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddressType() {
		return this.addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}