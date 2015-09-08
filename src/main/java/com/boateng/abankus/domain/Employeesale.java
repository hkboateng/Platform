package com.boateng.abankus.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the employeesales database table.
 * 
 */
@Entity
@Table(name="employeesales")
@NamedQuery(name="Employeesale.findAll", query="SELECT e FROM Employeesale e")
public class Employeesale implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int employeeSalesId;

	private Timestamp dateCreated;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="employeeId")
	private Employee employee;

	private CustomerAccount customeraccount;

	public Employeesale() {
	}

	public int getEmployeeSalesId() {
		return this.employeeSalesId;
	}

	public void setEmployeeSalesId(int employeeSalesId) {
		this.employeeSalesId = employeeSalesId;
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

	public CustomerAccount getCustomeraccount() {
		return this.customeraccount;
	}

	public void setCustomeraccount(CustomerAccount customeraccount) {
		this.customeraccount = customeraccount;
	}

}