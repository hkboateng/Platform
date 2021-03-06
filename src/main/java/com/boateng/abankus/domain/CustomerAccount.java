package com.boateng.abankus.domain;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;


/**
 * The persistent class for the customeraccount database table.
 * 
 */
@Entity
@Table(name="customeraccount")
@NamedQuery(name="CustomerAccount.findAll", query="SELECT c FROM CustomerAccount c")
@DynamicUpdate(value=true)
public class CustomerAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int customerAccount;

	@Transient
	private Map<String, Customer> customerMap = new HashMap<String,Customer>();
	
	@NotNull
	private String accountNumber;

	private Timestamp dateCreate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="employeeId", referencedColumnName="employeeId")
	private Employee employee;

	@NotNull
	private String status;

	private String notes;
	
	@NotNull
	private String industry;
	
	//bi-directional many-to-one association to Customer
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(value=org.hibernate.annotations.CascadeType.MERGE)
	@JoinColumn(name="customerId", referencedColumnName="customerId")
	private Customer customer;

	private String lastActivityDate;
	
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
		String customerNumber = customer.getCustomerNumber(); 
		if(!customerMap.containsKey(customerNumber)){
			customerMap.put(customerNumber, customer);
		}
		this.customer = customer;
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

	public boolean isCustomerActive(){
		boolean accountStatus = false;
		if(status.equals("Active")){
			accountStatus = true;
		}
		return accountStatus;
	}
	
	public boolean isCustomerAccountLocked(){
		boolean accountStatus = false;
		if(status.equals("Locked")){
			accountStatus = true;
		}		
		return accountStatus;
	}
	
	public Customer getCustomer(String customerNumber){
		Customer customer = null;
		if(customerMap.containsKey(customerNumber)){
			customer = customerMap.get(customerNumber);
		}
			
		return customer;
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

	public String getLastActivityDate() {
		return lastActivityDate;
	}

	public void setLastActivityDate(String lastActivityDate) {
		this.lastActivityDate = lastActivityDate;
	}
}