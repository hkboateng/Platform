package com.boateng.abankus.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the salesemployee database table.
 * 
 */
@Entity
@NamedQuery(name="Salesemployee.findAll", query="SELECT s FROM Salesemployee s")
public class Salesemployee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int saleEmployeeId;

	private Timestamp dateCreated;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="employeeId")
	private Employee employee;

	private long clientorderId;

	/**
	 * @return the clientOrder
	 */
	public long getClientOrder() {
		return clientorderId;
	}

	/**
	 * @param clientOrder the clientOrder to set
	 */
	public void setClientOrder(long clientOrder) {
		this.clientorderId = clientOrder;
	}

	public Salesemployee() {
	}

	public int getSaleEmployeeId() {
		return this.saleEmployeeId;
	}

	public void setSaleEmployeeId(int saleEmployeeId) {
		this.saleEmployeeId = saleEmployeeId;
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