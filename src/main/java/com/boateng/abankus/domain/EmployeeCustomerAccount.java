package com.boateng.abankus.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the employeecustomeraccount database table.
 * 
 */
@Entity
@Table(name="Employeecustomeraccount")
@NamedQuery(name="Employeecustomeraccount.findAll", query="SELECT e FROM EmployeeCustomerAccount e")
public class EmployeeCustomerAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int employeeSalesId;

	private int customerAccount;

	private Timestamp dateCreated;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="employeeId")
	private Employee employee;

	public EmployeeCustomerAccount() {
	}

	public int getEmployeeSalesId() {
		return this.employeeSalesId;
	}

	public void setEmployeeSalesId(int employeeSalesId) {
		this.employeeSalesId = employeeSalesId;
	}

	public int getCustomerAccount() {
		return this.customerAccount;
	}

	public void setCustomerAccount(int customerAccount) {
		this.customerAccount = customerAccount;
	}

	public Timestamp getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}