package com.boateng.abankus.domain;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.sql.Timestamp;


/**
 * The persistent class for the customeraccount database table.
 * 
 */
@Entity
@Table(name="customeraccount")
@NamedQuery(name="CustomerAccount.findAll", query="SELECT c FROM CustomerAccount c")
public class CustomerAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int customerAccount;

	@NotNull
	private String accountNumber;

	private Timestamp dateCreate;

	//bi-directional many-to-one association to Customer
	@NotNull
	@ManyToOne
	@JoinColumn(name="employeeId", referencedColumnName="employeeId")	
	private Employee employee;

	@NotNull
	private String status;

	private String notes;
	
	@NotNull
	private String industry;
	
	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="customerNumber", referencedColumnName="customerNumber")
	private Customer customer;

	public CustomerAccount() {
	}

	public int getCustomerAccount() {
		return this.customerAccount;
	}

	public void setCustomerAccount(int customerAccount) {
		this.customerAccount = customerAccount;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Timestamp getDateCreate() {
		return this.dateCreate;
	}

	public void setDateCreate(Timestamp dateCreate) {
		this.dateCreate = dateCreate;
	}



	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * @return the industry
	 */
	public String getIndustry() {
		return industry;
	}

	/**
	 * @param industry the industry to set
	 */
	public void setIndustry(String industry) {
		this.industry = industry;
	}

}