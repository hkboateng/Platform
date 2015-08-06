package com.boateng.abankus.domain;

import java.io.Serializable;

import javax.persistence.*;

import com.boateng.abankus.utils.SecurityUtils;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;


/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int customerId;

	private String company_name;

	private String contactPerson;

	private String customerType;

	private String firstname;

	private String gender;

	private String lastname;

	private String middlename;

	//bi-directional many-to-one association to Customeraccount
	@OneToMany(fetch = FetchType.EAGER,mappedBy="customer")
	@JsonManagedReference
	private List<CustomerAccount> customerAccounts;

	private String customerNumber;
	
	public Customer() {
	}
	/**
	 * @param companyName
	 * @param companyNumber
	 */
	public Customer(String firstname,String lastname, String companyType) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.customerType = companyType;
		
	}


	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public String getContactPerson() {
		return this.contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
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

	public List<CustomerAccount> getCustomeraccounts() {
		return this.customerAccounts;
	}

	public void setCustomeraccounts(List<CustomerAccount> customerAccounts) {
		this.customerAccounts = customerAccounts;
	}

	public CustomerAccount addCustomeraccount(CustomerAccount customerAccount) {
		getCustomeraccounts().add(customerAccount);
		customerAccount.setCustomer(this);

		return customerAccount;
	}

	public CustomerAccount removeCustomeraccount(CustomerAccount customerAccount) {
		getCustomeraccounts().remove(customerAccount);
		customerAccount.setCustomer(null);

		return customerAccount;
	}
	public String getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCompany_name() {
		return company_name;
	}


	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
}