package com.boateng.abankus.domain;

import java.io.Serializable;

import javax.persistence.*;

import com.boateng.abankus.employee.interfaces.Customers;
import com.boateng.abankus.utils.SecurityUtils;

import java.util.List;


/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable,Customers {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int customerId;

	@Column(name="company_name")
	private String companyName;

	private String companyNumber;

	private String customerType;

	private String firstname;

	private String lastname;

	private String middlename;

	//bi-directional many-to-one association to Address
	@OneToMany(mappedBy="customer")
	private List<Address> addresses;

	//bi-directional many-to-one association to Email
	@OneToMany(mappedBy="customer")
	private List<Email> emails;

	//bi-directional many-to-one association to Phone
	@OneToMany(mappedBy="customer")
	private List<Phone> phones;

	public Customer() {
	}


	/**
	 * @param companyName
	 * @param companyNumber
	 */
	public Customer(String companyName, String companyType) {
		this.companyName = companyName;
		this.customerType = companyType;
		this.companyNumber = SecurityUtils.generateCustomerId();
	}


	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyNumber() {
		return this.companyNumber;
	}

	public void setCompanyNumber(String companyNumber) {
		this.companyNumber = companyNumber;
	}

	public String getCustomerType() {
		return this.customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
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

	public List<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Address addAddress(Address address) {
		getAddresses().add(address);
		address.setCustomer(this);

		return address;
	}

	public Address removeAddress(Address address) {
		getAddresses().remove(address);
		address.setCustomer(null);

		return address;
	}

	public List<Email> getEmails() {
		return this.emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

	public Email addEmail(Email email) {
		getEmails().add(email);
		email.setCustomer(this);

		return email;
	}

	public Email removeEmail(Email email) {
		getEmails().remove(email);
		email.setCustomer(null);

		return email;
	}

	public List<Phone> getPhones() {
		return this.phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public Phone addPhone(Phone phone) {
		getPhones().add(phone);
		phone.setCustomer(this);

		return phone;
	}

	public Phone removePhone(Phone phone) {
		getPhones().remove(phone);
		phone.setCustomer(null);

		return phone;
	}

	@Override
	public String getCustomerNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCustomerNumber(String customerNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long getIndividualCustomerId() {
		// TODO Auto-generated method stub
		return null;
	}

}